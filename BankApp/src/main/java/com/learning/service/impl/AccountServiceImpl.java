package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.repo.AccountRepository;
import com.learning.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Override
	public Account addAccount(Account account) {
		
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(long id) {
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public String deleteAccountById(long id) {
		accountRepository.deleteById(id);
		return "success";
	}

	@Override
	public Account updateAccount(Account account) {
		return account;
	}

	@Override
	public List<Account> getAllAccountAscOrder() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> getAllAccountDescOrder() {
		return accountRepository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return accountRepository.existsById(id);
	}

}
