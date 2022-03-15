package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Beneficiary;

@Service
public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary);
	public Optional<Beneficiary> getBeneficiaryById(long id); 
	public List<Beneficiary> getAllBeneficiaries();
	public List<Beneficiary> getAllCustomerBeneficiaries(long id);
	public String deleteBeneficiaryById(long id);
	public Beneficiary updateBeneficiary(Beneficiary beneficiary);
	public List<Beneficiary> getAllBeneficiaryAscOrder();
	public List<Beneficiary> getAllBeneficiaryDescOrder();
	public boolean existsById(long id);
}
