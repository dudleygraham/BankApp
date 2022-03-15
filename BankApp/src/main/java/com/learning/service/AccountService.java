package com.learning.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.learning.entity.Account;

@Service
public interface AccountService {
	public Account addAccount(Account account);
	public Optional<Account> getAccountById(long id); 
	public List<Account> getAllAccounts();
	public String deleteAccountById(long id);
	public Account updateAccount(Account account);
	public List<Account> getAllAccountAscOrder();
	public List<Account> getAllAccountDescOrder();
	public boolean existsById(long id);
}