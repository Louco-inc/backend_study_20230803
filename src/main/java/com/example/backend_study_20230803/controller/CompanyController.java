package com.example.backend_study_20230803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    // CompanyFormから一つずつ要素を取り出して、Companyの配列に入れていく
    for(int index = 0; index < companyFormList.length; index++) {
      CompanyForm companyForm = companyFormList[index];

      // Companyエンティティのインスタンス化
      Company company = new Company();

      company.setCompanyName(companyForm.getCompanyName());
      company.setPrtimesUrl(companyForm.getPrtimesUrl());
      company.setEmail(companyForm.getEmail());
      company.setChargeEmployee(companyForm.getChargeEmployee());
      company.setCategory(companyForm.getCategory());

      companies[index] = company;
    }

    // Serviceクラスに会社の情報を複数一気に登録できるメソッドを作り、引数でcompanyの配列を渡す。
    service.postCompanyAll(companies);
  }
}