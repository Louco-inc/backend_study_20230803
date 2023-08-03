package com.example.backend_study_20230803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_study_20230803.entity.Company;
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
}
