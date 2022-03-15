package com.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.AccountRequest;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private CustomerService customerService;
	
	@PutMapping(value = "/{customerId}/account/{accountNo}")
	public ResponseEntity<?> approveCustomerAccount(@PathVariable("customerId") long customerId,
			@PathVariable("accountNo") long accountNum, @Valid @RequestBody AccountRequest accountRequest) {

		if (customerService.existsbyId(customerId) && accountNum == accountRequest.getAccountNumber()) {
			accountRequest.setApproved(accountRequest.getApproved());
		} else {
			return ResponseEntity.badRequest().body("Please check Account Number");
		}

		return ResponseEntity.status(403).body(accountRequest);
	}
}