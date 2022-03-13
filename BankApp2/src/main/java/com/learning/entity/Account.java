package com.learning.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;
import com.learning.enums.ApprovalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="accNo") })
@ToString
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long accNo;
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	
	private String customerName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;
	
	@Enumerated(EnumType.STRING)
	private ApprovalType appType;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
    @OneToMany
    private List<Beneficiary> beneficiaries;
    @OneToMany
    private List<Transaction> transactions;
    @NotNull
    private int balance;
}