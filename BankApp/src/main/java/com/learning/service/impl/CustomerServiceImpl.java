package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Customer;
import com.learning.repository.CustomerRepository;

import com.learning.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository repo;
	@Override
	public Customer addCustomer(Customer user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomersAscOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomersDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsbyId(long id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

}
