package com.learning.payload.response;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;
import com.learning.enums.ApprovalType;

import lombok.Data;
@Data
public class AccountResponse {
	private long accNo;
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	@Enumerated(EnumType.STRING)
	private ApprovalType appType;
	@ManyToOne
	private String customerName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;
	boolean approved;
}
