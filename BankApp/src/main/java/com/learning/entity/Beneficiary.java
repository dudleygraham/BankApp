package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.enums.ActiveType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int beneficiaryId;
	@NotNull
	private int beneficiaryAccountNo;
	@NotBlank
	private String beneficiaryName;
	@Enumerated(EnumType.STRING)
	private ActiveType active;
	
	@OneToOne
	private Account account;
}
