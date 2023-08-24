package com.example.backend_study_20230803.service;

import java.time.LocalDateTime;
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

  // 引数で受け取ったIDの会社情報を削除するメソッドを作成
  // repositoryのdeleteByIdメソッドを呼び出す
  public void deleteCompany(Integer id) {
    repository.deleteById(id);
  }

  // 引数で受け取った会社情報で更新するメソッドを作成
  // repositoryのsaveメソッドを呼び出す
  public void updateCompany(Company company) {
    repository.save(company);
  }

  // 引数で受け取ったnameで会社情報を検索するメソッドを作成
  // repositoryのfindByCompanyNameメソッドを呼び出す
  public List<Company> findByCompanyName(String companyName) {
    String companyNameLike = "%" + companyName + "%";
		return repository.findByCompanyNameLike(companyNameLike);
	}

  // 最新の会社情報1件を検索するためのメソッドを呼び出す
  // repositoryのfindTopOrderByCreatedDateDescメソッドを呼び出す
  public Company findFirstByOrderByCreatedDateDesc() {
		return repository.findFirstByOrderByCreatedDateDesc();
	}

  // 引数で受けとったreference_date以降に登録された会社情報を検索するメソッドを作成
  // repositoryのfindByCreatedDateGreaterThanEqualメソッドを呼び出す
  public List<Company> findByCreatedDateGreaterThanEqual(LocalDateTime referenceDate) {
		return repository.findByCreatedDateGreaterThanEqual(referenceDate);
	}

  // 会社情報を昇順で取得するためにrepositoryのメソッドを呼び出す
  public List<Company> findAllByOrderByCreatedDate() {
		return repository.findAllByOrderByCreatedDate();
	}

  // 会社情報を降順で取得するためにrepositoryのメソッドを呼び出す
  public List<Company> findAllByOrderByCreatedDateDesc() {
		return repository.findAllByOrderByCreatedDateDesc();
	}

  // 引数で受け取ったnameに前方一致する会社情報を検索するメソッドを作成
  // repositoryのfindByCompanyNameメソッドを呼び出す
  public List<Company> findByCompanyNameStartingWith(String companyName) {
		return repository.findByCompanyNameStartingWith(companyName);
	}

  // 引数で受け取ったemailLikeで会社情報を検索するメソッドを作成
  // repositoryのfindByEmailContainingメソッドを呼び出す
  public List<Company> findByEmail(String emailLike) {
		return repository.findByEmailContaining(emailLike);
	}

  // 引数で受け取ったcompanyNameとemailLike２つを検索条件に会社情報を検索するメソッドを作成
  // repositoryのfindByCompanyNameContainingAndEmailContainingメソッドを呼び出す
  public List<Company> findByCompanyNameAndEmail(String companyName, String emailLike) {
		return repository.findByCompanyNameContainingAndEmailContaining(companyName, emailLike);
	}
}
