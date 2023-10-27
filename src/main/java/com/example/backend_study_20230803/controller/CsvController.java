package com.example.backend_study_20230803.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.AbstractConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.example.backend_study_20230803.csv.CompanyCsv;
import com.example.backend_study_20230803.entity.Company;
import com.example.backend_study_20230803.form.CompanyForm;
import com.example.backend_study_20230803.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin
public class CsvController {

  @Autowired
  CompanyService service;

  @Autowired
  CompanyController companyController;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @GetMapping(value = "/export_companies/*.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
      + "; charset=UTF-8; Content-Disposition: attachment")
  @ResponseBody
  public Object getCompanyCsv() throws JsonProcessingException {

    // 最新のデータ1件を取得
    // Company company = service.findFirstByOrderByCreatedDateDesc();

    // DBの会社情報を全件取得
    List<Company> companies = service.findAll();

    // CSV出力テストデータ
    // List<CompanyCsv> companiesListCsv = new ArrayList<CompanyCsv>() {
    // {
    // add(new CompanyCsv(1001L, "aaa", "xxx", "email", "chargeEmployee",
    // "category", 0, false, "2999-12-31 23:59:59"));
    // add(new CompanyCsv(1002L, "bbb", "yyy", "email", "chargeEmployee",
    // "category", 0, false, "2999-12-31 23:59:59"));
    // add(new CompanyCsv(1003L, "ccc", "zzz", "email", "chargeEmployee",
    // "category", 0, false, "2999-12-31 23:59:59"));
    // }
    // };

    // Company型をCompanyCsv型に変換するためのBeanマッピングライブラリ「ModelMapper」をインスタンス化
    ModelMapper modelMapper = new ModelMapper();
    /*
     * Company型をそのままCompanyCsv型に変換するとcreatedDateが「2023-08-02T23:30:52」のような文字列となるため、
     * DateTimeFormatterで「yyyy-MM-dd HH:mm:ss」のような文字列になるようにModelMapperに変換ルールを追加
     */
    modelMapper.addConverter(new AbstractConverter<LocalDateTime, String>() {
      @Override
      protected String convert(LocalDateTime source) {
        if (source == null) {
          return null;
        }
        String localDateTimeStr = source.format(formatter);
        return localDateTimeStr;
      }
    });

    // DBの会社情報リストをCSV出力用データのリストに変換
    List<CompanyCsv> companiesListCsv = modelMapper.map(companies, new TypeToken<List<CompanyCsv>>() {
    }.getType());

    // Company型をCompanyCsv型に変換
    // CompanyCsv companyCsv = modelMapper.map(company, CompanyCsv.class);
    // companiesListCsv.add(companyCsv);

    CsvMapper csvMapper = new CsvMapper();
    CsvSchema schema = csvMapper.schemaFor(CompanyCsv.class).withHeader();
    return csvMapper.writer(schema).writeValueAsString(companiesListCsv);
  }

  @PostMapping(value = "/import_companies", params = "csv_import")
  public String csvImport(@RequestParam("csv_file") MultipartFile csvFile, Model model) {

    CSVParser parse = null;

    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
      CSVFormat csvFormat = CSVFormat.RFC4180.builder().setHeader().setSkipHeaderRecord(true).build();
      parse = new CSVParser(reader, csvFormat);

      List<CompanyForm> companiesFormList = new ArrayList<CompanyForm>();
      for (CSVRecord line : parse) {
        CompanyForm companyForm = new CompanyForm();
        companyForm.setCompanyName(line.get("会社名"));
        companyForm.setPrtimesUrl(line.get("サイトURL（PR TIMES）"));
        companyForm.setEmail(line.get("問い合わせ先（メール）"));
        companyForm.setChargeEmployee(line.get("担当社員名"));
        companyForm.setCategory(line.get("カテゴリー"));
        companyForm.setCreatedDate(formatter.format(LocalDateTime.now()));
        companiesFormList.add(companyForm);
      }

      CompanyForm[] companyFormArray = companiesFormList.toArray(new CompanyForm[companiesFormList.size()]);
      companyController.postCompanyAll(companyFormArray);

      model.addAttribute("message", "CSVファイルのインポートに成功しました！");
    } catch (IOException e) {
      model.addAttribute("message", "CSVファイルのインポートに失敗しました");
    }

    return "index";
  }

}
