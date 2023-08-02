package com.example.backend_study_20230803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_study_20230803.entity.Test;
import com.example.backend_study_20230803.service.TestService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class TestController {

	@Autowired
	TestService service;

	@RequestMapping(value = "/test_all")
	@CrossOrigin
	public List<Test> getAllTest() {
		List<Test> tests = service.findAll();
		return tests;
	}

	@RequestMapping(value = "/test/{id}")
	@CrossOrigin
	public Test getTest(@PathVariable Number id) {
		System.out.println(id);
		Test test = service.findById(id);
		return test;
	}
}
