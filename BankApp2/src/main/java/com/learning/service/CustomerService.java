package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer Customer);
	public Optional<Customer> getCustomerById(long id);
	public List<Customer> getAllCustomers();
	public String deleteCustomerById(long id);
	public Customer updateCustomer(Customer Customer);
	public List<Customer> getAllCustomersAscOrder();
	public List<Customer> getAllCustomersDescOrder();
	public boolean existsbyId(long id);
	List<Account> getAllCustomerAccounts(long id);
	List<Beneficiary> getAllCustomerBeneficiaries(long id);
}
