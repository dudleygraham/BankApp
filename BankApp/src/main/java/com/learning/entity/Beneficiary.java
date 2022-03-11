package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.ActiveType;
import com.learning.enums.ApprovalType;

import lombok.Data;
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="beneficiaryAccountNo") })
public class Beneficiary {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private long beneficiaryAccountNo;
  private String beneficiaryName;
  @Enumerated(EnumType.STRING)
  private ApprovalType approved;
  @ManyToOne
  private Account account;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateAdded;
}
