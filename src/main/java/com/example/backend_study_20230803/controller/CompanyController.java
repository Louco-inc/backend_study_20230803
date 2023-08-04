package com.example.backend_study_20230803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    System.out.println(companyForm.getCreatedDate());
    // company.setCreatedDate(companyForm.getCreatedDate());

    service.postCompany(company);
  }
}
