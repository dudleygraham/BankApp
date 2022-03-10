package com.learning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.IdNotFoundException;
import com.learning.entity.Customer;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.CustomerResponse;
import com.learning.repository.CustomerRepository;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService cs;
	@Autowired
	private CustomerResponse cr;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteByCustomerId(@PathVariable("id") long id){
		if (cs.existsbyId(id)) {
			cs.deleteCustomerById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			throw new NoDataFoundException("no user data");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {	
		Customer c = new Customer();
		c.setUsername(sr.getUsername());
		c.setFullname(sr.getName());
		c.setPassword(sr.getPassword());
		
		Customer customer = cs.addCustomer(c);
		return ResponseEntity.status(201).body(customer);
	}
	@GetMapping("/")
	public ResponseEntity<?> getAllCustomers(){
		List<Customer> customers = cs.getAllCustomers();
		List<CustomerResponse> crs = new ArrayList<>();
		customers.forEach(e->{CustomerResponse cr = new CustomerResponse();
		cr.setPassword(e.getPassword());
		cr.setName(e.getFullname());
		cr.setUsername(e.getUsername());
		crs.add(cr);
		});
		if(crs.size()>0) {
		return ResponseEntity.ok(crs);
		}
		else {
			throw new NoDataFoundException("no customer data");
		}
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id")long id) {
		
	Customer customer =	cs.getCustomerById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		// entity ===> UserResponse()
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setUsername(customer.getUsername());
	customerResponse.setName(customer.getFullname());
	customerResponse.setPassword(customer.getPassword());
	return ResponseEntity.status(200).body(customerResponse);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id")long id, String username) {
		
	Customer customer =	cs.getCustomerById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		// first get user
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setUsername(username);//is this it?
	customerResponse.setName(customer.getFullname());

	return ResponseEntity.status(200).body(customerResponse);
	}
}// get all customer, approve customer account, create account
