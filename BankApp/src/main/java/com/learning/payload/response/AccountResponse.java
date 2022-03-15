package com.learning.payload.response;


import org.springframework.format.annotation.DateTimeFormat;

import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
	private AccountType accountType;
	private double accountBalance;
	private Boolean approved;
	private int accountNumber;
	private DateTimeFormat dateOfCreation;
	
}
