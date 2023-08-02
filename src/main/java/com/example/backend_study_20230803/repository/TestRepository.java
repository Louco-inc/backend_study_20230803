package com.example.backend_study_20230803.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend_study_20230803.entity.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
	List<Test> findAll();
	Test findById(Number id);
}
