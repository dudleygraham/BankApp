package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary Beneficiary);
	public Optional<Beneficiary> getBeneficiaryById (Long id);
	public List<Beneficiary> getAllBeneficiaries();
	public String deleteBeneficiaryById(Long id);
	public Beneficiary updateBeneficiary(Beneficiary Beneficiary);
	public List<Beneficiary> getAllBeneficiariesAscOrder();
	public List<Beneficiary> getAllBeneficiariesDescOrder();
	public boolean existsbyId(long id);
}
