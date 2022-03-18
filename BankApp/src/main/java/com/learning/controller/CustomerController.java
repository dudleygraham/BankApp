package com.learning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.learning.entity.Role;
import com.learning.enums.AccountType;
import com.learning.enums.RoleType;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.AccountRequest;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.CustomerResponse;
import com.learning.security.jwt.JwtUtils;
import com.learning.payload.response.JwtResponse;

import com.learning.security.service.UserDetailsImpl;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;

import lombok.var;

@RestController
@RequestMapping("/customer")
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BeneficiaryService beneficiaryService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;

//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticate(@Valid @RequestBody Customer customer) {
//		List<Customer> customers = customerService.getAllCustomers();
//
//		for (Customer customerUser : customers) {
//			if (customerUser.equals(customer)) {
//				customerService.addCustomer(customer);
//
//				return ResponseEntity.status(200).body(customer);
//			}
//
//		}
//		return ResponseEntity.status(403).body(customer);
//	}
	
	@PostMapping("/authenticate")
//	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> authenticate(@Valid @RequestBody SignInRequest signInRequest)
	{
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateToken(authentication);
			
			UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
			
			List<String> roles = userDetailsImpl.getAuthorities()
					.stream()
					.map(e->e.getAuthority())
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), 
					userDetailsImpl.getUsername(), 
				
					roles));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body("Problem in sign in");
	}

//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticate(@Valid @RequestBody SignInRequest signInRequest){
//		List<Customer> customerList = customerService.getAllCustomers();
//		
//		
//		boolean wrongPassword = false;
//		boolean noSuchUser= false;
//		Customer c = new Customer();
//		for(int i = 0; i<customerList.size(); i++) {
//			if(signInRequest.getUsername().equals(customerList.get(i).getUsername())) {
//				if(signInRequest.getPassword().equals(customerList.get(i).getPassword())) {
//					c = customerList.get(i);
//					return ResponseEntity.status(200).body(c);
//					
//				}wrongPassword = true;
//			}
//		}noSuchUser = true;
//		if(wrongPassword) {
//			return ResponseEntity.badRequest().body("wrong password");
//		}
//		if(noSuchUser) {
//			return ResponseEntity.badRequest().body("no such user");
//		}return ResponseEntity.status(200).body(c);
//	}
//	@PostMapping("/register")
//	@PreAuthorize("hasRole('CUSTOMER')")
//	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {
//
//		Customer cust = new Customer();
//
//		cust.setUsername(sr.getUsername());
//
//		cust.setFullname(sr.getFullname());
//		String newPassword = passwordEncoder.encode(sr.getPassword());
//		cust.setPassword(newPassword);
//		
//		Customer custmer = customerService.addCustomer(cust);
//
//		return ResponseEntity.status(201).body(custmer);
//	}
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody  SignupRequest signupRequest){
		Set<Role> roles = new HashSet<>();
		Role userRole = new Role();
		userRole.setRoleName(RoleType.CUSTOMER);//orElseThrow(new NoDataFoundException("no role with that name"));
		
		roles.add(userRole);
		
		
		Customer customer = new Customer();
 		System.out.println("Sign up request" + signupRequest);
		//customer.setAadhar(signupRequest.getAadhar());
		//customer.setPan(signupRequest.getPan());
 		customer.setUsername(signupRequest.getUsername());
 		customer.setPassword(signupRequest.getPassword());
 		customer.setFullname(signupRequest.getFullname());
 		customer.setRoles(roles);
		Customer user2 = customerService.addCustomer(customer);
		
		return ResponseEntity.status(201).body(user2);	
		// 400 401 500 501 201 200 301 302 404 503
	}

	@PostMapping(value = "/{customerId}/account")
	//@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest accountRequest,
			@PathVariable("customerId") long customerId) {
		Customer customer = new Customer();
		if (customerService.existsbyId(customerId)) {
			Set<Account> accounts = new HashSet<>();
			for (Account account : customer.getCustomerAccounts()) {
				account.setAccountBalance(accountRequest.getAccountBalance());
				account.setAccountType(accountRequest.getAccounttype());
				account.setApprovalType(accountRequest.getApprovalType());
				accounts.add(account);
			}
			customer.setCustomerAccounts(accounts);
			return ResponseEntity.status(200).body(customer);
		} 
		return ResponseEntity.badRequest().body("Account cannot be created");
	}
	
	@GetMapping(value = "/{customerId}/account")
//	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getAllCustomerAccounts(@PathVariable("customerId") long customerId) {
		if (customerService.existsbyId(customerId)) {
			for (Account account : customerService.getAllCustomerAccounts(customerId)) {
				return ResponseEntity.status(200).body(account);
			}
		}
		return ResponseEntity.badRequest().body("No accounts created for the customer");
	} 
	
//	@PostMapping(value = "/{customerId}/beneficiary")
//	public ResponseEntity<?> addBeneficiaryAccount(@PathVariable("customerId")long customerId, @Valid @RequestBody  )
//	{
//		Customer customer = new Customer();
//		if (customerService.existsbyId(customerId)) {
//			Set<Beneficiary> beneficiaries = new HashSet<>();
//			for (Beneficiary beneficiary : customer.getBeneficiaries()) {
//				if(beneficiary.getBeneficiaryAccountNo())
//				{
//					
//				}
//			}
//			customer.setBeneficiaries(beneficiaries);
//			return ResponseEntity.status(200).body(customer);
//		} 
//		return ResponseEntity.badRequest().body("Beneficiary with" + "not added");
//	}
	
	@DeleteMapping(value = "/{customerId}/beneficiary/{beneficiaryId}")
	public ResponseEntity<?> deleteBeneficiaryById(@PathVariable("customerId") long customerId, @PathVariable("beneficiaryId") long beneficiaryId)
	{
		if (customerService.existsbyId(customerId)) {
			String deletedBeneficiary = beneficiaryService.deleteBeneficiaryById(beneficiaryId);
			return ResponseEntity.status(200).body(deletedBeneficiary);
		}
		return ResponseEntity.badRequest().body("Beneficiary not deleted");
	}
	
	@GetMapping(value = "/{customerId}/beneficiary")
	public ResponseEntity<?> getAllBeneficiaryAccounts(@PathVariable("customerId") long customerId)
	{
		if (customerService.existsbyId(customerId)) {
			for (Account account : customerService.getAllCustomerAccounts(customerId)) {
				return ResponseEntity.status(200).body(account);
			}
		}
		return ResponseEntity.badRequest().body("No accounts created for the customer");
	}
	
//	@GetMapping("/{username}/forgot/question/answer")
//	public ResponseEntity<?> forgotPassword(@PathVariable("username") String username, Customer customer)
//	{
//		if (username == customer.getSecretQuestion()) {
//			customer.setPassword(customer.getPassword());
//			
//			Customer newCustomer = customerService.addCustomer(customer);
//			return ResponseEntity.status(200).body(newCustomer);
//		}
//		return ResponseEntity.badRequest().body("Sorry password not updated");
//	}
	
	@PutMapping("/{username}/forgot")
	public ResponseEntity<?> forgotPassword(@PathVariable("username") String username, Customer customer)
	{
		if (username == customer.getUsername()) {
			customer.setPassword(customer.getPassword());
			
			Customer newCustomer = customerService.addCustomer(customer);
			return ResponseEntity.status(200).body(newCustomer);
		}
		return ResponseEntity.badRequest().body("Sorry password not updated");
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCustomer() {
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerResponse> crs = new ArrayList<>();
		customers.forEach(e -> {
			CustomerResponse customerResponse = new CustomerResponse();
			customerResponse.setPassword(e.getPassword());
			customerResponse.setFullname(e.getFullname());
			customerResponse.setUsername(e.getUsername());
			crs.add(customerResponse);
		});
		if (crs.size() > 0) {
			return ResponseEntity.ok(crs);
		} else {
			throw new NoDataFoundException("no customer data");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") long id) {
		Customer customer = customerService.getCustomerById(id).get();
				//.orElseThrow(() -> new NoDataFoundException("no data found"));
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping(value = "/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId) {
		if (customerService.existsbyId(customerId)) {
			customerService.deleteCustomerById(customerId);
			return ResponseEntity.noContent().build();
		} else {
			throw new NoDataFoundException("no user data");
		}
	}

	@PutMapping(value = "/{customerId}")
	public ResponseEntity<?> updateCustomerById(@Valid @RequestBody Customer customer,
			@PathVariable("customerId") long customerId) {
		Map<String, Object> map = new HashMap<>();
		if (customerService.existsbyId(customerId)) {
			Customer existing = customerService.getCustomerById(customerId).get();
			existing.setFullname(customer.getFullname());
			existing.setPhone(customer.getPhone());
			existing.setPan(customer.getPan());
			existing.setAadhar(customer.getAadhar());
			existing.setSecretQuestion(customer.getSecretAnswer());
			existing.setSecretAnswer(customer.getSecretAnswer());
		}
		map.put("message", "success");
		map.put("data", customer);// this object it will transfrom it into JSON (implicitly).
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}

}
