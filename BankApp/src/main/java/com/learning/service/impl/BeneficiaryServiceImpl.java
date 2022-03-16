package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Beneficiary;
import com.learning.repository.BeneficiaryRepository;
import com.learning.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	
	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary) {

		return beneficiaryRepository.save(beneficiary);
	}

	@Override
	public Optional<Beneficiary> getBeneficiaryById(long id) {
		return beneficiaryRepository.findById(id);
	}

	@Override
	public List<Beneficiary> getAllBeneficiaries() {
		return beneficiaryRepository.findAll();
	}

	@Override
	public String deleteBeneficiaryById(long id) {
		beneficiaryRepository.deleteById(id);
		return "success";
	}

	@Override
	public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
		return beneficiary;
	}

	@Override
	public List<Beneficiary> getAllBeneficiaryAscOrder() {
		return beneficiaryRepository.findAll();
	}

	@Override
	public List<Beneficiary> getAllBeneficiaryDescOrder() {
		return beneficiaryRepository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return beneficiaryRepository.existsById(id);
	}

	@Override
	public List<Beneficiary> getAllCustomerBeneficiaries(long id) {
		
		return beneficiaryRepository.findAll();
	}

}
