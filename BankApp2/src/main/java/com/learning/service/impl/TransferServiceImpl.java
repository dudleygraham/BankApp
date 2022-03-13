package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Transfer;
import com.learning.repository.TransferRepository;
import com.learning.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {
	
	TransferRepository transferRepository;
	
	@Override
	public Transfer addTransfer(Transfer transfer) {
		return transferRepository.save(transfer);
	}

	@Override
	public Optional<Transfer> getTransferById(long id) {
		return transferRepository.findById(id);
	}

	@Override
	public List<Transfer> getAllTransfers() {
		return transferRepository.findAll();
	}

	@Override
	public String deleteTransferById(long id) {
		transferRepository.deleteById(id);
		return "success";
	}

	@Override
	public Transfer updateTransfer(Transfer transfer) {
		return transfer;
	}

	@Override
	public List<Transfer> getAllTransfersAscOrder() {
		return transferRepository.findAll();
	}

	@Override
	public List<Transfer> getAllTransfersDescOrder() {
		return transferRepository.findAll();
	}

	@Override
	public boolean existsbyId(long id) {
		return transferRepository.existsById(id);
	}

}
