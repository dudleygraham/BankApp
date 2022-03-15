package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Account;

import com.learning.entity.Customer;



public interface CustomerService {
	public Customer addCustomer(Customer user);
	public Optional<Customer> getCustomerById (long id);
	public List<Customer> getAllCustomers();
	public List<Account> getAllCustomerAccounts(long id);
	public void deleteCustomerById(long id);
	public Customer updateCustomer(Customer customer);
	public boolean existsbyId(long id);
	public List<Customer> getAllCustomerAscOrder();
	public List<Customer> getAllCustomerDescOrder();
}