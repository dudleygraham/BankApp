package com.learning.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entity.Customer;
import com.learning.exception.NoDataFoundException;
import com.learning.repository.CustomerRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByUsername(username)
				.orElseThrow(() -> new NoDataFoundException("username not found with username" + username));
		System.out.println(customer);
		return UserDetailsImpl.build(customer);
	}
}
