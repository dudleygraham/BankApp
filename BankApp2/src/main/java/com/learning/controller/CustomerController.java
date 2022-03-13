package com.learning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Transfer;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.AccountRequest;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.CustomerResponse;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.TransferResponse;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
	@Autowired
	private CustomerService cs;
	@Autowired
	private AccountService as;
	@Autowired
	private BeneficiaryService bs;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	JwtUtils jwtUtils;
//	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticate(@Valid @RequestBody SignInRequest signInRequest)
//	{
//		try {
//			Authentication authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			String jwt = jwtUtils.generateToken(authentication);
//			
//			UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
//			
//			List<String> roles = userDetailsImpl.getAuthorities()
//					.stream()
//					.map(e->e.getAuthority())
//					.collect(Collectors.toList());
//			
//			return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), 
//					userDetailsImpl.getUsername(), 
//					roles));
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		return ResponseEntity.badRequest().body("Problem in sign in");
//	}
//	@PostMapping("/register")
//	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {	
//		Customer c = new Customer();
//		c.setUsername(sr.getUsername());
//		c.setFullname(sr.getFullname());
//		c.setPassword(sr.getPassword());
//		
//		Customer customer = cs.addCustomer(c);
//		return ResponseEntity.status(201).body(customer);
//	}
	@GetMapping("/")
	@PreAuthorize("hasRole('CUSTOMER')")
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
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> deleteByCustomerId(@PathVariable("id") long id){
		if (cs.existsbyId(id)) {
			cs.deleteCustomerById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			throw new NoDataFoundException("no user data");
		}
	}
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
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
	@PostMapping(value = "/{customerId}/account")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest accountRequest,
			@PathVariable("customerId") long customerId) {
		Customer customer = new Customer();
		if (cs.existsbyId(customerId)) {
			Set<Account> accounts = new HashSet<>();
			for (Account account : customer.getAccounts()) {
				account.setBalance(accountRequest.getBalance());
				account.setAccType(accountRequest.getAccType());
				account.setAppType(accountRequest.getAppType());
				accounts.add(account);
			}
			customer.setAccounts(accounts);
			return ResponseEntity.status(200).body(customer);
		} 
		return ResponseEntity.badRequest().body("Account cannot be created");
	}
	
	@GetMapping(value = "/{customerId}/account")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getAllCustomerAccounts(@PathVariable("customerId") long customerId) {
		if (cs.existsbyId(customerId)) {
			for (Account account : cs.getAllCustomerAccounts(customerId)) {
				return ResponseEntity.status(200).body(account);
			}
		}
		return ResponseEntity.badRequest().body("No accounts created for the customer");
	}
	@PostMapping(value = "/{customerId}/beneficiary")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> addBeneficiaryAccount(@PathVariable("customerId")long customerId, long accNo, Beneficiary b)
	{
		if (cs.existsbyId(customerId) && accNo == b.getAccount().getAccNo() ) {
			as.getAccountById(accNo).orElseThrow(()->new NoDataFoundException("No such account")).getBeneficiaries().add(b);
			return ResponseEntity.status(200).body(b);	
		}
		return ResponseEntity.status(200).body("Sorry, Beneficiary With "+accNo +" not added");
	}
	@DeleteMapping(value = "/{customerId}/beneficiary/{beneficiaryId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> deleteBeneficiaryById(@PathVariable("customerId") long customerId, @PathVariable("beneficiaryId") long beneficiaryId)
	{
		if (cs.existsbyId(customerId)) {
			String deletedBeneficiary = bs.deleteBeneficiaryById(beneficiaryId);
			return ResponseEntity.status(200).body(deletedBeneficiary);
		}
		return ResponseEntity.badRequest().body("Beneficiary not deleted");
	}
	@PutMapping(value = "/transfer")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> transfer(Transfer t, Customer c){
		Account donor = as.getAccountById(t.getFromAccNumber()).orElseThrow(()->new NoDataFoundException("Account not found"));
		Account recep = as.getAccountById(t.getToAccNumber()).orElseThrow(()->new NoDataFoundException("Account not found"));
		int amount = t.getAmount();
		int original = recep.getBalance();
		recep.setBalance(original + amount);
		original = donor.getBalance();
		donor.setBalance(original- amount);
		TransferResponse tr = new TransferResponse();
		tr.setFromAccNumber(t.getFromAccNumber());
		tr.setToAccNumber(t.getToAccNumber());
		tr.setAmount(amount);
		tr.setReason(t.getReason());
		tr.setBy(c.getFullname());
		return ResponseEntity.status(200).body(tr);
	}
	@PutMapping(value="/:username/forgot")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> forgot(String username, String password){
		List<Customer> customers = cs.getAllCustomers();
		boolean found= false;
		Customer c = new Customer();
		for(int i = 0; found = false; i++) {
			if (customers.get(i).getUsername().equals(username)) {
				found = true;
				c = customers.get(i);
			}
		}
		if(found) {
			c.setPassword(password);
			return ResponseEntity.status(200).body("Password updated");
		}
		return ResponseEntity.status(403).body("Sorry, password not updated");
	}
	@GetMapping(value="/:username/forgot/question/answer")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> answerQuestion(String username, String answer) {
		List<Customer> customers = cs.getAllCustomers();
		boolean found= false;
		Customer c = new Customer();
		for(int i = 0; found = false; i++) {
			if (customers.get(i).getUsername().equals(username) 
					&& customers.get(i).getSecretAnswer().equals(answer)) {
				found = true;
			}
		}
		if(found) {
			return ResponseEntity.status(200).body("Details validated");
		}
		return ResponseEntity.status(403).body("Sorry, details did not validate");
	}
}
