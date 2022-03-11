package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.repository.AccountRepository;
import com.learning.repository.CustomerRepository;

import com.learning.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepo;
	AccountRepository accountRepo;
	@Override
	public Customer addCustomer(Customer user) {
		// TODO Auto-generated method stub
		return customerRepo.save(user);
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
		return customerRepo.existsById(id);
	}
	@Override
	public List<Account> getAllCustomerAccounts(long id) 
	{
		List<Account> accountsCustomer = null;

		if (customerRepo.existsById(id)) {
			accountsCustomer = accountRepo.findAll();
		}
		
		return accountsCustomer;
	}
	@Override
	public List<Beneficiary> getAllCustomerBeneficiaries(long id){
		Customer c = customerRepo.findById(id);
		Set<Account> a = c.getAccounts();
		List<Beneficiary> b = new ArrayList<>();
		a.forEach(e->{List<Beneficiary> b1 = e.getBeneficiaries();
		b1.forEach(f->{b.add(f);});
		});
		
		return b;
	}


}
