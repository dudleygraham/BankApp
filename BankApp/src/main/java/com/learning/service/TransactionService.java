package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Transaction;

public interface TransactionService {
	public Transaction addTransaction(Transaction Transaction);
	public Optional<Transaction> getTransactionById (Long id);
	public List<Transaction> getAllTransactions();
	public String deleteTransactionById(Long id);
	public Transaction updateTransaction(Transaction Transaction);
	public List<Transaction> getAllTransactionsAscOrder();
	public List<Transaction> getAllTransactionsDescOrder();
	public boolean existsbyId(long id);
}
