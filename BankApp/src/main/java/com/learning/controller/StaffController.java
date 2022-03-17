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
import com.learning.payload.request.SignInRequest;
import com.learning.payload.response.AccountResponse;
import com.learning.payload.response.ApproveResponse;
import com.learning.payload.response.BeneficiaryResponse;
import com.learning.payload.response.CustomerResponse;
import com.learning.payload.response.StatementResponse;
import com.learning.payload.response.TransferResponse;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StaffController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BeneficiaryService beneficiaryService;
	@Autowired
	private StaffService staffService;

	private CustomerResponse customerResponse;
	@GetMapping(value = "/account/{accountNo}")
	public ResponseEntity<?> getStatement(
			@PathVariable("accountNo") long accountNum, @Valid @RequestBody AccountRequest accountRequest) {
		StatementResponse statementResponse = new StatementResponse();
		if ( accountNum == accountRequest.getAccountNumber()) {
			statementResponse.setBalance(accountRequest.getAccountBalance());
			statementResponse.setTransactions(accountRequest.getTransactions());
			statementResponse.setAccountNumber(accountRequest.getAccountNumber());
			statementResponse.setCustomerName(accountRequest.getCustomer().getFullname());
		} else {
			return ResponseEntity.badRequest().body("Please check Account Number");
		}

		return ResponseEntity.status(403).body(accountRequest);
	}
	@PutMapping(value = "/{customerId}/account/{accountNo}")
	public ResponseEntity<?> approveCustomerAccount(@PathVariable("customerId") long customerId,
			@PathVariable("accountNo") long accountNum, @Valid @RequestBody AccountRequest accountRequest) {

		if (customerService.existsbyId(customerId) && accountNum == accountRequest.getAccountNumber()) {
			accountRequest.setApprovalType(accountRequest.getApprovalType());
		} else {
			return ResponseEntity.badRequest().body("Please check Account Number");
		}

		return ResponseEntity.status(403).body(accountRequest);
	}
	@GetMapping(value ="accounts/approve")
	public ResponseEntity<?> getPendingAccounts(){
		List<Account> pendingAccounts = accountService.getAllAccounts();
		List<AccountResponse> accountResponseList = new ArrayList();
		pendingAccounts.forEach(e->{AccountResponse accountResponse = new AccountResponse();
		accountResponse.setAccType(e.getAccountType());
		accountResponse.setCustomerName(e.getCustomerName());
		accountResponse.setAccNo(e.getAccountNumber());
		accountResponse.setDateCreated(e.getDateOfCreation());
		accountResponse.setAppType(e.getApprovalType());
		accountResponseList.add(accountResponse);
		});
		if(accountResponseList.size()>0) {
			return ResponseEntity.ok(accountResponseList);
			}
			else {
				throw new NoDataFoundException("no account data");
			}
	}
	@PutMapping(value ="accounts/approve")
	public ResponseEntity<?> approvePendingAccount(@PathVariable("accountNo") long accountNum, Staff s){
		Account account = accountService.getAccountById(accountNum).orElseThrow(()->new NoDataFoundException("Approving of account was not successful"));
		ApproveResponse approveResponse = new ApproveResponse();
		approveResponse.setAccNo(account.getAccountNumber());
		approveResponse.setCustomerName(account.getCustomerName());
		approveResponse.setStaffName(s.getStaffName());
		approveResponse.setAccType(account.getAccountType());
		approveResponse.setAppType(account.getApprovalType());
		approveResponse.setDateCreated(account.getDateOfCreation());
		return ResponseEntity.status(200).body(approveResponse);
	}
	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerResponse> customerResponseList = new ArrayList<>();
		customers.forEach(e->{CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomerId(e.getCustomerId());
		customerResponse.setFullname(e.getFullname());
		customerResponse.setEnabled(EnableType.Enable);
		customerResponseList.add(customerResponse);
		});
		if(customerResponseList.size()>0) {
		return ResponseEntity.ok(customerResponseList);
		}
		else {
			throw new NoDataFoundException("no customer data");
		}
	}
	@PutMapping(value="/customer")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id")long id, EnableType status) {
		
	Customer customer =	customerService.getCustomerById(id);
		
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setEnabled(status);
	customerResponse.setFullname(customer.getFullname());

	return ResponseEntity.status(200).body(customerResponse);
	}
	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id")long id) {
		
	Customer customer =	customerService.getCustomerById(id);
		
	CustomerResponse customerResponse=  new CustomerResponse();
	customerResponse.setCustomerId(customer.getCustomerId());
	customerResponse.setFullname(customer.getFullname());
	customerResponse.setEnabled(customer.getEnabled());
	customerResponse.setDateCreated(customer.getDateCreated());
	return ResponseEntity.status(200).body(customerResponse);
	}
	@PutMapping(value = "/transfer")
	public ResponseEntity<?> transfer(Transfer transfer, Staff staff){
		Account donor = accountService.getAccountById(transfer.getFromAccNumber()).orElseThrow(()->new NoDataFoundException("Account not found"));
		Account recep = accountService.getAccountById(transfer.getToAccNumber()).orElseThrow(()->new NoDataFoundException("Account not found"));
		double amount = transfer.getAmount();
		double original = recep.getAccountBalance();
		recep.setAccountBalance(original + amount);
		original = donor.getAccountBalance();
		donor.setAccountBalance(original- amount);
		TransferResponse transferResponse = new TransferResponse();
		transferResponse.setFromAccNumber(transfer.getFromAccNumber());
		transferResponse.setToAccNumber(transfer.getToAccNumber());
		transferResponse.setAmount(amount);
		transferResponse.setReason(transfer.getReason());
		transferResponse.setBy(staff.getStaffName());
		return ResponseEntity.status(200).body(transferResponse);
	}
	@PutMapping(value = "/beneficiary")
	public ResponseEntity<?> approveBeneficiary(BeneficiaryRequest beneficiaryRequest)
	{
		if(accountService.existsById(beneficiaryRequest.getAccount().getAccountNumber())) {
			BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
			beneficiaryResponse.setAccount(beneficiaryRequest.getAccount());
			beneficiaryResponse.setApproved(ApprovalType.Yes);
			beneficiaryResponse.setBeneficiaryAccountNo(beneficiaryRequest.getBeneficiaryAccountNo());
			beneficiaryResponse.setBeneficiaryName(beneficiaryRequest.getBeneficiaryName());
			beneficiaryResponse.setDateAdded(beneficiaryRequest.getDateAdded());
			return ResponseEntity.status(200).body(beneficiaryResponse);
		}
		
		return ResponseEntity.badRequest().body("Beneficiary not changed");
	}
	@GetMapping(value = "/beneficiary")
	public ResponseEntity<?> getBeneficiaries()
	{
		List<Beneficiary> beneficiaryList = beneficiaryService.getAllBeneficiaries();
		if(beneficiaryList.size()>0) {
			List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
			beneficiaryList.forEach(e->{
				BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
				beneficiaryResponse.setFromCustomerAccNo(e.getAccount().getAccountNumber());
				beneficiaryResponse.setBeneficiaryAccountNo(e.getBeneficiaryAccountNo());
				beneficiaryResponse.setDateAdded(e.getDateAdded());
				beneficiaryResponse.setApproved(e.getApproved());
				beneficiaryResponseList.add(beneficiaryResponse);
			});
			return ResponseEntity.status(200).body(beneficiaryResponseList);
		}
		return ResponseEntity.badRequest().body("No beneficiary to approve");
	}
	public ResponseEntity<?> authenticate(@Valid @RequestBody SignInRequest signInRequest){
		List<Staff> staffList = staffService.getAllStaffs();
		
			boolean wrongPassword = false;
		boolean noSuchUser= false;
		Staff s = new Staff();
		for(int i = 0; i<staffList.size(); i++) {
			if(signInRequest.getUsername() == staffList.get(i).getStaffUserName()) {
				if(signInRequest.getPassword()==staffList.get(i).getStaffPassword()) {
					s = staffList.get(i);
					return ResponseEntity.status(200).body(s);
					
				}wrongPassword = true;
			}noSuchUser = true;
		}
		if(wrongPassword) {
			return ResponseEntity.badRequest().body("wrong password");
		}
		if(noSuchUser) {
			return ResponseEntity.badRequest().body("no such user");
		}return ResponseEntity.status(200).body(s);
	}
}