package com.learning.payload.response;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.entity.Account;
import com.learning.enums.ApprovalType;

import lombok.Data;

@Data
public class BeneficiaryResponse {
	private long beneficiaryAccountNo;
	private long fromCustomerAccNo;
	  private String beneficiaryName;
	  @Enumerated(EnumType.STRING)
	  private ApprovalType approved;
	  @ManyToOne
	  private Account account;
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate dateAdded;
}
