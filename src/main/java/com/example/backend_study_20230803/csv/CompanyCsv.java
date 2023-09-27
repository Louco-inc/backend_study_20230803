package com.example.backend_study_20230803.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "会社ID", "会社名", "サイトURL（PR TIMES）", "問い合わせ先（メール）", "担当社員名", "カテゴリー",
    "従業員数", "上場有無", "登録日時" })
public class CompanyCsv {
  @JsonProperty("会社ID")
  private long companyId;
  @JsonProperty("会社名")
  private String companyName;
  @JsonProperty("サイトURL（PR TIMES）")
  private String prtimesUrl;
  @JsonProperty("問い合わせ先（メール）")
  private String email;
  @JsonProperty("担当社員名")
  private String chargeEmployee;
  @JsonProperty("カテゴリー")
  private String category;
  @JsonProperty("従業員数")
  private int numberOfEmployees;
  @JsonProperty("上場有無")
  private boolean isListed;
  @JsonProperty("登録日時")
  private String createdDate;

  public CompanyCsv() {
    super();
  }

  public CompanyCsv(long companyId, String companyName, String prtimesUrl, String email, String chargeEmployee,
      String category, int numberOfEmployees, boolean isListed, String createdDate) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.prtimesUrl = prtimesUrl;
    this.email = email;
    this.chargeEmployee = chargeEmployee;
    this.category = category;
    this.numberOfEmployees = numberOfEmployees;
    this.isListed = isListed;
    this.createdDate = createdDate;
  }
}
