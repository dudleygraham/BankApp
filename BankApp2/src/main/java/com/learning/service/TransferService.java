package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Transfer;

public interface TransferService {
	public Transfer addTransfer(Transfer Transfer);
	public Optional<Transfer> getTransferById (long id);
	public List<Transfer> getAllTransfers();
	public String deleteTransferById(long id);
	public Transfer updateTransfer(Transfer Transfer);
	public List<Transfer> getAllTransfersAscOrder();
	public List<Transfer> getAllTransfersDescOrder();
	public boolean existsbyId(long id);
}
