package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Transfer;

@Service
public interface TransferService {
	public Transfer addTransfer(Transfer transfer);
	public Optional<Transfer> getTransferById(long id); 
	public List<Transfer> getAllTransfers();
	public String deleteTransferById(long id);
	public Transfer updateTransfer(Transfer transfer);
	public List<Transfer> getAllTransfersAscOrder();
	public List<Transfer> getAllTransfersDescOrder();
	public boolean existsById(long id);

}
