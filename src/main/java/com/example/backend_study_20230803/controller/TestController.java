package com.example.backend_study_20230803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend_study_20230803.entity.Test;
import com.example.backend_study_20230803.form.CompanyForm;
import com.example.backend_study_20230803.service.CompanyService;
import com.example.backend_study_20230803.service.TestService;

@Controller
public class TestController {

	// @Autowired
	// TestService service;

	// @RequestMapping(value = "/test_all")
	// @CrossOrigin
	// public List<Test> getAllTest() {
	// List<Test> tests = service.findAll();
	// return tests;
	// }

	// @RequestMapping(value = "/test/{id}")
	// @CrossOrigin
	// public Test getTest(@PathVariable Number id) {
	// Test test = service.findById(id);
	// return test;
	// }

	@Autowired
	CompanyService service;

	@GetMapping("/home")
	public String home(Model model, @ModelAttribute CompanyForm book) {
		model.addAttribute("companies", service.findAll());
		return "companies/index";
	}
}
