package com.example.backend_study_20230803.form;

// import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CompanyForm {
	private String companyName;
	private String prtimesUrl;
	private String email;
	private String chargeEmployee;
	private String category;
	private String createdDate;

	// 指定した日付より前の期間に登録されたデータを検索するかどうかのフラグ
	// trueの場合：指定した日付【以前】の期間を検索
	// falseの場合：指定した日付【以降】の期間を検索（デフォルト値）
	private Boolean isBeforeTime = false;
}
