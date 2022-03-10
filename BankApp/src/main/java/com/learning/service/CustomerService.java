package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer Customer);
	public Optional<Customer> getCustomerById (Long id);
	public List<Customer> getAllCustomers();
	public String deleteCustomerById(Long id);
	public Customer updateCustomer(Customer Customer);
	public List<Customer> getAllCustomersAscOrder();
	public List<Customer> getAllCustomersDescOrder();
	public boolean existsbyId(long id);
}
