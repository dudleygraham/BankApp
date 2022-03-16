package com.learning.payload.request;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.entity.Customer;
import com.learning.entity.Transaction;
import com.learning.enums.AccountType;
import com.learning.enums.ApprovalType;

import lombok.Data;

@Data
public class AccountRequest {
	private AccountType accounttype;
    private double accountBalance; 
    //private Boolean approved;
    @Enumerated(EnumType.STRING)
	private ApprovalType approvalType;
	private int accountNumber;
	private Date dateOfCreation;
	private List<Transaction> transactions;
	private Customer customer;
}


