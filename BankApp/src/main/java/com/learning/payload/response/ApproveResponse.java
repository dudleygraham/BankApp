package com.learning.payload.response;

import java.sql.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.entity.Transaction;
import com.learning.enums.AccountType;
import com.learning.enums.ApprovalType;

import lombok.Data;

@Data
public class ApproveResponse {
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	private long accNo;
	private String staffName;
	private String CustomerName;
	private Date dateCreated;
	@Enumerated(EnumType.STRING)
	private ApprovalType appType;
}
