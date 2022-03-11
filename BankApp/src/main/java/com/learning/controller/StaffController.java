package com.learning.controller;
import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.entity.Transfer;
import com.learning.enums.ApprovalType;
import com.learning.enums.EnableType;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.AccountRequest;
import com.learning.payload.request.BeneficiaryRequest;
import com.learning.payload.response.AccountResponse;
import com.learning.payload.response.ApproveResponse;
import com.learning.payload.response.BeneficiaryResponse;
import com.learning.payload.response.CustomerResponse;
import com.learning.payload.response.StatementResponse;
import com.learning.payload.response.TransferResponse;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private CustomerService cs;
	@Autowired
	private AccountService as;
	@Autowired
	private BeneficiaryService bs;
	@Autowired
	private CustomerResponse cr;
	@GetMapping(value = "/account/{accountNo}")
	public ResponseEntity<?> getStatement(
			@PathVariable("accountNo") long accountNum, @Valid @RequestBody AccountRequest accountRequest) {
		StatementResponse sr = new StatementResponse();
		if ( accountNum == accountRequest.getAccNo()) {
			sr.setBalance(accountRequest.getBalance());
			sr.setTransactions(accountRequest.getTransactions());
			sr.setAccNo(accountRequest.getAccNo());
			sr.setCustomerName(accountRequest.getCustomer().getFullname());
		} else {
			return ResponseEntity.badRequest().body("Please check Account Number");
		}

		return ResponseEntity.status(403).body(accountRequest);
	}
	@PutMapping(value = "/{customerId}/account/{accountNo}")
	public ResponseEntity<?> approveCustomerAccount(@PathVariable("customerId") long customerId,
			@PathVariable("accountNo") long accountNum, @Valid @RequestBody AccountRequest accountRequest) {

		if (cs.existsbyId(customerId) && accountNum == accountRequest.getAccNo()) {
			accountRequest.setAppType(accountRequest.getAppType());
		} else {
			return ResponseEntity.badRequest().body("Please check Account Number");
		}

		return ResponseEntity.status(403).body(accountRequest);
	}
	@GetMapping(value ="accounts/approve")
	public ResponseEntity<?> getPendingAccounts(){
		List<Account> pa = as.getAllAccounts();
		List<AccountResponse> ars = new ArrayList();
		pa.forEach(e->{AccountResponse ar = new AccountResponse();
		ar.setAccType(e.getAccType());
		ar.setCustomerName(e.getCustomer().getFullname());
		ar.setAccNo(e.getAccNo());
		ar.setDateCreated(e.getDateCreated());
		ar.setAppType(e.getAppType());
		ars.add(ar);
		});
		if(ars.size()>0) {
			return ResponseEntity.ok(ars);
			}
			else {
				throw new NoDataFoundException("no account data");
			}
	}
	@GetMapping(value ="accounts/approve")
	public ResponseEntity<?> approvePendingAccount(@PathVariable("accountNo") long accountNum, Staff s){
		Account a = as.getAccountById(accountNum).orElseThrow(()->new NoDataFoundException("Approving of account was not successful"));
		ApproveResponse ar = new ApproveResponse();
		ar.setAccNo(a.getAccNo());
		ar.setCustomerName(a.getCustomer().getFullname());
		ar.setStaffName(s.getStaffName());
		ar.setAccType(a.getAccType());
		ar.setAppType(a.getAppType());
		ar.setDateCreated(a.getDateCreated());
		return ResponseEntity.status(200).body(ar);
	}
	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers(){
		List<Customer> customers = cs.getAllCustomers();
		List<CustomerResponse> crs = new ArrayList<>();
		customers.forEach(e->{CustomerResponse cr = new CustomerResponse();
		cr.setId(e.getCustomerId());
		cr.setName(e.getFullname());
		cr.setEnabled(EnableType.Enable);
		crs.add(cr);
		});
		if(crs.size()>0) {
		return ResponseEntity.ok(crs);
		}
		else {
			throw new NoDataFoundException("no customer data");
		}
	}
	@PutMapping(value="/customer")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id")long id, EnableType status) {
		
	Customer customer =	cs.getCustomerById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setEnabled(status);
	customerResponse.setName(customer.getFullname());

	return ResponseEntity.status(200).body(customerResponse);
	}
	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id")long id) {
		
	Customer customer =	cs.getCustomerById(id).orElseThrow(()->new NoDataFoundException("Customer not found"));
		
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setId(customer.getCustomerId());
	customerResponse.setName(customer.getFullname());
	customerResponse.setEnabled(customer.getEnabled());
	customerResponse.setDateCreated(customer.getDateCreated());
	return ResponseEntity.status(200).body(customerResponse);
	}
	@PutMapping(value = "/transfer")
	public ResponseEntity<?> transfer(Transfer t, Staff s){
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
		tr.setBy(s.getStaffName());
		return ResponseEntity.status(200).body(tr);
	}
	@PutMapping(value = "/beneficiary")
	public ResponseEntity<?> approveBeneficiary(BeneficiaryRequest bq)
	{
		if(as.existsbyId(bq.getAccount().getAccNo())) {
			BeneficiaryResponse br = new BeneficiaryResponse();
			br.setAccount(bq.getAccount());
			br.setApproved(ApprovalType.Yes);
			br.setBeneficiaryAccountNo(bq.getBeneficiaryAccountNo());
			br.setBeneficiaryName(bq.getBeneficiaryName());
			br.setDateAdded(bq.getDateAdded());
			return ResponseEntity.status(200).body(br);
		}
		
		return ResponseEntity.badRequest().body("Beneficiary not changed");
	}
	@GetMapping(value = "/beneficiary")
	public ResponseEntity<?> getBeneficiaries()
	{
		List<Beneficiary> bl = bs.getAllBeneficiaries();
		if(bl.size()>0) {
			List<BeneficiaryResponse> brs = new ArrayList<>();
			bl.forEach(e->{
				BeneficiaryResponse br = new BeneficiaryResponse();
				br.setFromCustomerAccNo(e.getAccount().getAccNo());
				br.setBeneficiaryAccountNo(e.getBeneficiaryAccountNo());
				br.setDateAdded(e.getDateAdded());
				br.setApproved(e.getApproved());
				brs.add(br);
			});
			return ResponseEntity.status(200).body(brs);
		}
		return ResponseEntity.badRequest().body("No beneficiary to approve");
	}
}