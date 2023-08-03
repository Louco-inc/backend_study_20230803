package com.example.backend_study_20230803.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_study_20230803.entity.Company;
import com.example.backend_study_20230803.repository.CompanyRepository;

@Service
public class CompanyService {
  @Autowired
  CompanyRepository repository;

  public List<Company> findAll() {
		return repository.findAll();
	}

	public Company findById(Number id) {
		return repository.findById(id);
	}
}
