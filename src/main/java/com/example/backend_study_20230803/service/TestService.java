package com.example.backend_study_20230803.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_study_20230803.entity.Test;
import com.example.backend_study_20230803.repository.TestRepository;

@Service
public class TestService {
	@Autowired
	TestRepository repository;

	public List<Test> findAll() {
		return repository.findAll();
	}

	public Test findById(Number id) {
		return repository.findById(id);
	}
}
