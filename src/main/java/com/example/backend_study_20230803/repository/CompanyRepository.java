package com.example.backend_study_20230803.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend_study_20230803.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
  List<Company> findAll();
	Company findById(Number id);
}
