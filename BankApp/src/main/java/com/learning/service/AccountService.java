package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Account;

public interface AccountService {
	public Account addAccount(Account Account);
	public Optional<Account> getAccountById (Long id);
	public List<Account> getAllAccounts();
	public String deleteAccountById(Long id);
	public Account updateAccount(Account Account);
	public List<Account> getAllAccountsAscOrder();
	public List<Account> getAllAccountsDescOrder();
	public boolean existsbyId(long id);
}
