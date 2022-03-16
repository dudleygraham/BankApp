package com.learning.payload.response;

import com.learning.entity.Customer;
import com.learning.enums.AccountType;


import lombok.Data;


import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.ApprovalType;


@Data
public class AccountResponse {
	private long accNo;
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	@Enumerated(EnumType.STRING)
	private ApprovalType appType;
	@ManyToOne
	private Customer customer;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;
	boolean approved;
	private String customerName;

}
