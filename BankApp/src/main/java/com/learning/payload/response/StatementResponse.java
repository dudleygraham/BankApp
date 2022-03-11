package com.learning.payload.response;

import java.util.List;

import com.learning.entity.Transaction;

import lombok.Data;

@Data
public class StatementResponse {
	private long accNo;
	private String customerName;
	private int balance;
	private List<Transaction> transactions;
}
