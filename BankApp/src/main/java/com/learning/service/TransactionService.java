package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Transaction;


public interface TransactionService {

	public Transaction addTransaction(Transaction transaction);
	public Optional<Transaction> getTransactionById(long id); 
	public List<Transaction> getAllTransactions();
	public String deleteTransactionById(long id);
	public Transaction updateTransaction(Transaction transaction);
	public List<Transaction> getAllTransactionAscOrder();
	public List<Transaction> getAllTransactionDescOrder();
	public boolean existsById(long id);
	
//	public void sendMoney(long fromAccount, double amount);
//	
//	public void getMoney(long toAccount, double amount);
//
//	public void transferMoney(long fromAccount, long toAccount, double Amount);
}
