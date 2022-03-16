package com.learning.payload.response;

import java.util.List;

import com.learning.entity.Transaction;

import lombok.Data;

@Data
public class StatementResponse {
	private long accountNumber;
	private String customerName;
	private double balance;
	private List<Transaction> transactions;
}
