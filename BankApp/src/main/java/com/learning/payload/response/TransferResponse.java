package com.learning.payload.response;

import lombok.Data;

@Data
public class TransferResponse {
	private long fromAccNumber;
	  private long toAccNumber;
	  private double amount;
	  private String reason;
	  private String by;
}
