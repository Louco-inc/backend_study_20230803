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

  public void postCompany(Company company) {
    repository.save(company);
  }

  // Serviceクラスに会社の情報を複数一気に登録できるメソッドを作り、引数でcompanyの配列を受け取る。
  // 受け取ったcompanyの配列をsaveAllのメソッドに渡す。
  public void postCompanyAll(Company[] companies) {
    for(int index = 0; index < companies.length; index++) {
      repository.save(companies[index]);
    }
  }
}
