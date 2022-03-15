package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transfer {
	@Id
	private int transferId;
	@NotNull
	private int fromAccNumber;
	@NotNull
	private int toAccNumber;
	@NotNull
	private double amount;
	@NotBlank
	private String reason;
	@OneToOne
	private Customer by;
	@OneToOne
	private Account statement;
}
