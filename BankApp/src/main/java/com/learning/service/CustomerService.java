package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Account;

import com.learning.entity.Customer;



public interface CustomerService {
	public Customer addCustomer(Customer user);
	public Optional<Customer> getCustomerById (Long id);
	public List<Customer> getAllCustomers();
	public List<Account> getAllCustomerAccounts(Long id);
	public void deleteCustomerById(Long id);
	public Customer updateCustomer(Customer customer);
	public boolean existsbyId(Long id);
	public List<Customer> getAllCustomerAscOrder();
	public List<Customer> getAllCustomerDescOrder();
	public boolean existsByUsername(String username);
	public Customer getCustomerByUsername(String user);
}