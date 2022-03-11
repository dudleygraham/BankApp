package com.learning.entity;

import java.sql.Date;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.learning.enums.TransactionType;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="transactionId") })
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transactionId;
  private DateTimeFormat date;
  private String reference;
  private int amount;
  @Enumerated(EnumType.STRING)
  private TransactionType dbcr;
}
