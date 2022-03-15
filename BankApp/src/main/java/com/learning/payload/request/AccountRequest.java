package com.learning.payload.request;

import java.util.Date;

import com.learning.enums.AccountType;

import lombok.Data;

@Data
public class AccountRequest {
	private AccountType accounttype;
    private double accountBalance; 
    private Boolean approved;
	private int accountNumber;
	private Date dateOfCreation;
}
