package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="transactionId") })
@ToString
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transactionId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	private String reference;
	private int amount;
	@Enumerated(EnumType.STRING)
	private TransactionType dbcr;
}
