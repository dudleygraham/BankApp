package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.ActiveType;
import com.learning.enums.ApprovalType;

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
	private ApprovalType approved;
	
	@OneToOne
	private Account account;
	@JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate dateAdded;
}
