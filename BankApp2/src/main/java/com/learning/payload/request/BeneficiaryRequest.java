package com.learning.payload.request;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.entity.Account;
import com.learning.enums.ActiveType;
import com.learning.enums.ApprovalType;

import lombok.Data;
@Data
public class BeneficiaryRequest {
	private long beneficiaryAccountNo;
	  private String beneficiaryName;
	  @Enumerated(EnumType.STRING)
	  private ApprovalType approved;
	  @ManyToOne
	  private Account account;
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate dateAdded;
}
