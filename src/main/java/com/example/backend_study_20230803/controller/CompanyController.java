package com.example.backend_study_20230803.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_study_20230803.entity.Company;
import com.example.backend_study_20230803.form.CompanyForm;
import com.example.backend_study_20230803.service.CompanyService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CompanyController {

  @Autowired
  CompanyService service;

  @RequestMapping(value = "/companies")
  @CrossOrigin
  public List<Company> getAllTest() {
    List<Company> companies = service.findAll();
    return companies;
  }

  @RequestMapping(value = "/company/{id}")
  @CrossOrigin
  public Company getTest(@PathVariable Number id) {
    Company company = service.findById(id);
    return company;
  }

  @RequestMapping(value = "/companies", method = RequestMethod.POST)
  @CrossOrigin
  public void postCompany(@RequestBody CompanyForm companyForm) {
    Company company = new Company();

    company.setCompanyName(companyForm.getCompanyName());
    company.setPrtimesUrl(companyForm.getPrtimesUrl());
    company.setEmail(companyForm.getEmail());
    company.setChargeEmployee(companyForm.getChargeEmployee());
    company.setCategory(companyForm.getCategory());

    // company.setCreatedDate(companyForm.getCreatedDate());

    service.postCompany(company);
  }

  // 新しくメソッドを作成（会社情報を複数登録する用）
  // 会社情報を複数受け取れるようにする
  // 会社情報の配列を何らかの処理をして一つずつ取り出す（companyFormの配列から、
  // companyの配列に変換したい）
  // Serviceクラスに会社の情報を複数一気に登録できるメソッドを作り、引数でcompanyの配列を渡す。
  @RequestMapping(value = "/companies_all", method = RequestMethod.POST)
  @CrossOrigin
  public void postCompanyAll(@RequestBody CompanyForm[] companyFormList) {

    // Companyの配列を定義
    Company[] companies = new Company[companyFormList.length];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // CompanyFormから一つずつ要素を取り出して、Companyの配列に入れていく
    for (int index = 0; index < companyFormList.length; index++) {
      CompanyForm companyForm = companyFormList[index];

      // Companyエンティティのインスタンス化
      Company company = new Company();

      company.setCompanyName(companyForm.getCompanyName());
      company.setPrtimesUrl(companyForm.getPrtimesUrl());
      company.setEmail(companyForm.getEmail());
      company.setChargeEmployee(companyForm.getChargeEmployee());
      company.setCategory(companyForm.getCategory());
      Random rand = new Random();
      company.setNumberOfEmployees(rand.nextInt(9991) + 10);

      company.setIsListed(index % 4 == 0);

      String dateStr = companyForm.getCreatedDate();
      if (Character.isDigit(dateStr.charAt(0))) {
        company.setCreatedDate(LocalDateTime.parse(dateStr, formatter));
      }

      companies[index] = company;
    }

    // Serviceクラスに会社の情報を複数一気に登録できるメソッドを作り、引数でcompanyの配列を渡す。
    service.postCompanyAll(companies);
  }

  // 会社情報を削除するためのメソッドを作成する
  // 削除したいデータ１件を受け取る
  // Serviceクラスに１件の会社情報を削除するメソッドを作り、引数で１件の会社データを渡す
  @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
  @CrossOrigin
  public void deleteCompany(@PathVariable Integer id) {
    service.deleteCompany(id);
  }

  // 会社情報を更新するためのメソッドを作成する
  // 更新したい内容をパラメータとして受け取る
  // 受け取ったパラメータを元に既存の会社情報を取得
  // 取得した会社情報を書き換える
  // Serviceクラスに会社情報を更新するメソッドを作り、引数として書き換えた会社情報を渡す
  @RequestMapping(value = "/company/{id}", method = RequestMethod.PATCH)
  @CrossOrigin
  public void updateCompany(@RequestBody CompanyForm companyForm, @PathVariable Number id) {
    // 受け取ったパラメータを元に既存の会社情報を取得
    Company company = service.findById(id);

    company.setCompanyName(companyForm.getCompanyName());
    // company.setPrtimesUrl(companyForm.getPrtimesUrl());
    // company.setEmail(companyForm.getEmail());
    // company.setChargeEmployee(companyForm.getChargeEmployee());
    // company.setCategory(companyForm.getCategory());

    // company.setCreatedDate(companyForm.getCreatedDate());

    service.updateCompany(company);
  }

  // 会社情報をさまざまなパラメータ値で部分一致検索するためのメソッドを作成します
  // パラメータは会社名（name）とメールアドレス（email）の２つ
  // エンドポイントで指定したパラメータの値を引数として受け取る
  // Serviceクラスの会社情報を指定パラメータによって検索するためのメソッドを呼び出し、引数としてパラメータの値を渡す
  @RequestMapping(value = "/companies/search")
  @CrossOrigin
  public List<Company> searchCompanies(@RequestParam(name = "name", defaultValue = "") String companyName,
      @RequestParam(name = "email", defaultValue = "") String email) {

    List<Company> companies = null;

    if (!companyName.isEmpty() && !email.isEmpty()) {
      // どちらも空文字ではない場合は、両方を検索条件に検索
      companies = service.findByCompanyNameAndEmail(companyName, email);
    } else if (!companyName.isEmpty()) {
      companies = service.findByCompanyName(companyName);
    } else if (!email.isEmpty()) {
      companies = service.findByEmail(email);
    } else {
      // どちらも空文字の場合は全件検索
      companies = service.findAll();
    }
    return companies;
  }

  // 特定の会社名とカテゴリの組み合わせに対応するデータを取得するためのメソッドを作成する
  // リクエストボディで指定したcompanyNameとcategoryを引数として受け取る
  // リクエストボディを受け取るために、RequestMappingで「RequestMethod.POST」を指定する
  // Serviceクラスの会社名とカテゴリの組み合わせで会社情報を検索するメソッドを呼び出し、引数としてcompanyNameとcategoryの値を渡す
  @RequestMapping(value = "/companies/search", method = RequestMethod.POST)
  @CrossOrigin
  public List<Company> searchCompaniesByCompanyNameAndCategory(@RequestBody CompanyForm companyForm) {
    List<Company> companies = service.findByCompanyNameAndCategory(companyForm.getCompanyName(),
        companyForm.getCategory());
    return companies;
  }

  // 最新のデータ1件を取得するためのメソッドを作成する
  // Serviceクラスの最新の会社情報1件を検索するためのメソッドを呼び出す
  @RequestMapping(value = "/companies/search/latest")
  @CrossOrigin
  public Company searchCompaniesNew() {
    Company company = service.findFirstByOrderByCreatedDateDesc();
    return company;
  }

  // 基準日以降に登録された会社情報を取得するためのメソッドを作成する
  // エンドポイントで指定した基準日（reference_date：yyyy-mm-dd）の値を引数として受け取る
  // Serviceクラスの会社情報を検索するためのメソッドを呼び出し、引数としてreference_dateの値を渡す
  @RequestMapping(value = "/companies/search/date")
  @CrossOrigin
  public List<Company> searchCompaniesByDate(@RequestParam("reference_date") String referenceDateStr) {
    // 文字列reference_dateをLocalDateTime型に変換
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // 時間指定がない場合は、LocalDate型でパースする
    LocalDate referenceDate = LocalDate.parse(referenceDateStr, formatter);
    // LocalDate型を[yyyy-MM-dd 00:00:00]としてLocalDateTime型に変換
    LocalDateTime referenceDateTime = referenceDate.atStartOfDay();

    List<Company> companies = service.findByCreatedDateGreaterThanEqual(referenceDateTime);
    return companies;
  }

  // データを日時の昇順/降順で並び替えて取得するためのメソッドを作成する
  // エンドポイントで指定した順序のパラメータ（orderPrm：asc/昇順、desc/降順）の値を引数として受け取る
  // パラメータが「asc」または空白（デフォルト）の場合：Serviceクラスの会社情報を昇順で取得するためのメソッドを呼び出す
  // パラメータが「desc」の場合：Serviceクラスの会社情報を降順で取得するためのメソッドを呼び出す
  // 上記以外のパラメータの場合：何も返さない
  @RequestMapping(value = "/companies/order_by/created_date")
  @CrossOrigin
  public List<Company> getAllOrderByCreatedDate(@RequestParam("orderParam") String orderParam) {
    List<Company> companies = null;
    switch (orderParam) {
      case "desc":
        companies = service.findAllByOrderByCreatedDateDesc();
        break;
      case "asc":
      default:
        companies = service.findAllByOrderByCreatedDate();
        break;
    }
    return companies;
  }

  // 会社名がエンドポイントで指定した文字列に前方一致する会社情報を検索するためのメソッドを作成する
  // エンドポイントで指定したnameの値を引数として受け取る
  // Serviceクラスの会社情報を前方一致検索するためのメソッドを呼び出し、引数としてnameの値を渡す
  @RequestMapping(value = "/companies/prefix_search")
  @CrossOrigin
  public List<Company> prefixSearchCompanies(@RequestParam("name") String companyName) {
    List<Company> companies = service.findByCompanyNameStartingWith(companyName);
    return companies;
  }

  // 特定のメールアドレスを持つ担当者のデータを取得するためのメソッドを作成する
  // レスポンスは担当者のみの配列を返す
  // エンドポイントで指定したemailの値を引数として受け取る
  // Serviceクラスのメールアドレスによって会社情報を検索するためのメソッドを呼び出し、引数としてemailの値を渡す
  // 取得したCompanyのListからそれぞれ担当者（chargeEmployee）を取り出し、別のListに格納する
  @RequestMapping(value = "/charge_employees/search")
  @CrossOrigin
  public List<String> searchChargeEmployee(@RequestParam(name = "email", defaultValue = "") String email) {
    List<Company> companies = service.findByEmail(email);

    // 取得したCompanyのListからそれぞれ担当者（chargeEmployee）を取り出して格納するためのListを定義
    List<String> chargeEmployees = new ArrayList<String>();

    String chargeEmployee;
    for (Company company : companies) {
      chargeEmployee = company.getChargeEmployee();

      // 担当者がnullでない、かつ、空文字出ない場合のみchargeEmployeesに追加
      if (chargeEmployee != null && !chargeEmployee.isEmpty()) {
        chargeEmployees.add(chargeEmployee);
      }
    }

    return chargeEmployees;
  }

  // 特定のカテゴリに属し、かつ特定の日時以前 or 以降に登録されたデータを取得するメソッドを作成する
  // リクエストボディで指定したcategory、createdDate、isBeforeTimeを引数として受け取る
  // リクエストボディを受け取るために、RequestMappingで「RequestMethod.POST」を指定する
  // isBeforeTime（真偽値）でcreatedDate以前（true）を検索するか、以降（false）を検索するか指定する（デフォルトはfalse）
  // isBeforeTimeが「true」の場合：Serviceクラスの特定の日付【以前】に登録された会社情報を取得するためのメソッドを呼び出す
  // isBeforeTimeが「false」の場合：Serviceクラスの特定の日付【以降】に登録された会社情報を取得するためのメソッドを呼び出す
  @RequestMapping(value = "/companies/search/by_category", method = RequestMethod.POST)
  @CrossOrigin
  public List<Company> SearchCompaniesByCategoryAndDate(@RequestBody CompanyForm companyForm) {
    // リクエストボディで指定したcategoryを取得
    String category = companyForm.getCategory();

    // リクエストボディで指定したcreatedDateを取得し、基準日（referenceDate）としてLocalDate型に変換
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate referenceDate = LocalDate.parse(companyForm.getCreatedDate(), formatter);
    // LocalDate型を[yyyy-MM-dd 00:00:00]としてLocalDateTime型に変換
    LocalDateTime referenceDateTime = referenceDate.atStartOfDay();

    List<Company> companies = null;
    if (companyForm.getIsBeforeTime()) {
      companies = service.findByCategoryAndCreatedDateLessThanEqual(category, referenceDateTime);
    } else {
      companies = service.findByCategoryAndCreatedDateGreaterThanEqual(category, referenceDateTime);
    }

    return companies;
  }

  // データをページングして、idの小さい順から20件ずつ取得するメソッドを作成する（会社名で検索をかける場合を想定する）
  // レスポンスは、リクエストボディで指定したページ番号（pageNumber）の会社情報を返す
  // 例えば、１ページ目は検索でヒットした１〜20番目のデータ、2ページ目は21〜40番目のデータ、というように取得する
  // Pageableを用いてページのサイズ（１ページに何件表示させるか：今回は20）と、リクエストボディで指定したページ番号を設定する
  @RequestMapping(value = "/companies/search/by_name", method = RequestMethod.POST)
  @CrossOrigin
  public List<Company> pagingSearchCompaniesByCompanyName(@RequestBody CompanyForm companyForm) {
    // リクエストボディで指定した会社名、ページ番号を取得
    String companyName = companyForm.getCompanyName();
    int pageNumber = companyForm.getPageNumber();

    // ページサイズ（１ページあたり何件表示するか）を指定
    int pageSize = 20;

    // ページのサイズと、リクエストボディで指定したページ番号を設定する
    // ofSize(int pageSize)：pageSize を指定して、最初のページ（ページ番号 0）の新しい Pageable を作成します。
    // withPage(int pageNumber)：pageNumber が適用された新しい Pageable を作成します。
    Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber-1);

    Page<Company> companyPage = service.findByCompanyNameOrderByCompanyId(companyName, pageable);

    // getContentでListとして取得する
    List<Company> companies = companyPage.getContent();
    return companies;
  }

}