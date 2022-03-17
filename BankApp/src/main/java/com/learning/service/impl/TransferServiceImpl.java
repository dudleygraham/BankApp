package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Transfer;
import com.learning.service.TransferService;
@Service
public class TransferServiceImpl implements TransferService {

	@Override
	public Transfer addTransfer(Transfer Transfer) {
		return null;
	}

	@Override
	public Optional<Transfer> getTransferById(long id) {

		return null;
	}

	@Override
	public List<Transfer> getAllTransfers() {
	
		return null;
	}

	@Override
	public String deleteTransferById(long id) {

		return null;
	}

	@Override
	public Transfer updateTransfer(Transfer Transfer) {
	
		return null;
	}

	@Override
	public List<Transfer> getAllTransfersAscOrder() {
		return null;
	}

	@Override
	public List<Transfer> getAllTransfersDescOrder() {
	
		return null;
	}

	@Override
	public boolean existsById(long id) {
		
		return false;
	}

}
