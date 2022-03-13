package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Transaction;
import com.learning.repository.TransactionRepository;
import com.learning.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	TransactionRepository transactionRepository;
	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Optional<Transaction> getTransactionById(long id) {
		return transactionRepository.findById(id);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public String deleteTransactionById(long id) {
		transactionRepository.deleteById(id);
		return "success";
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		return transaction;
	}

	@Override
	public List<Transaction> getAllTransactionsAscOrder() {
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> getAllTransactionsDescOrder() {
		return transactionRepository.findAll();
	}

	@Override
	public boolean existsbyId(long id) {
		return transactionRepository.existsById(id);
	}

}
