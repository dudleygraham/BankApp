package com.learning.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.repo.AccountRepository;
import com.learning.repo.BeneficiaryRepository;
import com.learning.repo.CustomerRepository;
import com.learning.service.CustomerService;

import com.learning.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	BeneficiaryRepository beneficiaryRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(long id) {
		return customerRepo.findById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepo.findAll();
	}

	@Override
	public List<Account> getAllCustomerAccounts(long id) {
		List<Account> accountsCustomer = null;

		if (customerRepo.existsById(id)) {
			accountsCustomer = accountRepo.findAll();
		}

		return accountsCustomer;
	}

	public List<Beneficiary> getAllCustomerBeneficiaries(long id) {
		List<Beneficiary> beneficiaryCustomer = null;

		if (customerRepo.existsById(id)) {
			beneficiaryCustomer = beneficiaryRepo.findAll();
		}
		return beneficiaryCustomer;
	}

	@Override
	public void deleteCustomerById(long id) {
		customerRepo.deleteById(id);
	}

	public List<Customer> getAllCustomerAscOrder() {
		return customerRepo.findAll();
	}

	public List<Customer> getAllCustomerDescOrder() {
		return customerRepo.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		
		return null;
	}

	@Override
	public boolean existsbyId(long id) {
		
		return false;
	}

}
