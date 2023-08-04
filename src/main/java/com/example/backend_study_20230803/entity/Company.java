package com.example.backend_study_20230803.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class Company {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_generator")
  // @SequenceGenerator(name = "company_id_generator", sequenceName = "company_id_seq", allocationSize = 1)
	private Long companyId;

  @Column
	private String companyName;

  @Column
	private String prtimesUrl;

  @Column
	private String email;

  @Column
	private String chargeEmployee;

  @Column
	private String category;

  @Column
	private LocalDateTime createdDate;
}
