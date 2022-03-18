package com.learning.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Transaction;
import com.learning.repository.AccountRepository;
import com.learning.repository.TransactionRepository;
import com.learning.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;
	
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
	public List<Transaction> getAllTransactionAscOrder() {
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> getAllTransactionDescOrder() {
		return transactionRepository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return transactionRepository.existsById(id);
	}

	@Override
	public void sendMoney(long fromAccount, BigDecimal amount) {

		Account frmAccount = accountRepository.findCustomerAccountById(fromAccount).get();
	
		BigDecimal balance = frmAccount.getAccountBalance();
		
		if (balance.compareTo(amount) > 0) {

			BigDecimal sendAmount = balance.add(amount.negate());
			frmAccount.setAccountBalance(sendAmount);

			accountRepository.save(frmAccount);
		} else {
			throw new TransferException("Account balance not vaild");
		}

	}

	@Override
	public void getMoney(long toAccount, BigDecimal amount) {
		
		Account tAccount = accountRepository.findCustomerAccountById(toAccount).get();

		BigDecimal balance = tAccount.getAccountBalance().add(amount);

		tAccount.setAccountBalance(balance);

		accountRepository.save(tAccount);
		

	}

	@Override
	@Transactional(rollbackFor = TransferException.class)
	public void transferMoney(long fromAccount, long toAccount, BigDecimal amount) {
		sendMoney(fromAccount, amount);
		getMoney(toAccount, amount);
	}
	

}
