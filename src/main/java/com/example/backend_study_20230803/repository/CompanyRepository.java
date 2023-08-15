package com.example.backend_study_20230803.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend_study_20230803.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
  List<Company> findAll();
	Company findById(Number id);

  // 会社名で検索をするためのfindByCompanyNameメソッドを作成
  List<Company> findByCompanyNameLike(String companyNameLike);

  // 最新の会社情報1件を検索するためのメソッドを作成
  Company findFirstByOrderByCreatedDateDesc();

  // 基準日以降に登録された会社情報を検索するためのメソッドを作成
  List<Company> findByCreatedDateGreaterThanEqual(LocalDateTime referenceDate);


}
