package com.example.backend_study_20230803.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
  public List<Company> findByEmail(String email) {
		return repository.findByEmailContaining(email);
	}

  // 引数で受け取ったcompanyNameとemail２つを検索条件に会社情報を検索するメソッドを作成
  // repositoryのfindByCompanyNameContainingAndEmailContainingメソッドを呼び出す
  public List<Company> findByCompanyNameAndEmail(String companyName, String email) {
		return repository.findByCompanyNameContainingAndEmailContaining(companyName, email);
	}

  // 引数で受け取ったcompanyNameとcategory２つを検索条件に会社情報を検索するメソッドを作成
  // repositoryのfindByCompanyNameContainingAndEmailContainingメソッドを呼び出す
  public List<Company> findByCompanyNameAndCategory(String companyName, String category) {
		return repository.findByCompanyNameContainingAndCategoryContaining(companyName, category);
	}

  // 引数で受け取ったcategoryに属し、referenceDateTime以前に登録された会社情報を検索するメソッドを作成
  // repositoryのfindByCategoryContainingAndCreatedDateLessThanEqualメソッドを呼び出す
  public List<Company> findByCategoryAndCreatedDateLessThanEqual(String category, LocalDateTime referenceDateTime) {
		return repository.findByCategoryContainingAndCreatedDateLessThanEqual(category, referenceDateTime);
	}

  // 引数で受け取ったcategoryに属し、referenceDateTime以降に登録された会社情報を検索するメソッドを作成
  // repositoryのfindByCategoryContainingAndCreatedDateGreaterThanEqualメソッドを呼び出す
  public List<Company> findByCategoryAndCreatedDateGreaterThanEqual(String category, LocalDateTime referenceDateTime) {
		return repository.findByCategoryContainingAndCreatedDateGreaterThanEqual(category, referenceDateTime);
	}

  // 引数で受け取ったcompanyNameを検索条件に会社情報を検索し、pageableを元にページングするメソッドを作成
  // repositoryのfindByCompanyNameContainingOrderByCompanyIdメソッドを呼び出す
  public Page<Company> findByCompanyNameOrderByCompanyId(String companyName, Pageable pageable){
    return repository.findByCompanyNameContainingOrderByCompanyId(companyName, pageable);
  }
}
