package com.learning.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountId;
	
	private int accountNumber;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	private double accountBalance;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfCreation;
	
	private String accountStatus;
	
	private Boolean approved;
	
	@OneToMany
	private List<Transaction> transactions;
	
	private long customerId;
}
