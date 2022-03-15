package com.learning.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.learning.enums.DbcrType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {
	@Id
	private int transactionId;
	private Date date;
	@NotBlank
	private String reference;
	@NotNull
	private double amount;
	@Enumerated(EnumType.STRING)
	private DbcrType debitCredit;
}
