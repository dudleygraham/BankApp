package com.learning.entity;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.enums.TransactionType;

import org.springframework.format.annotation.DateTimeFormat;

public class Transaction {
  private DateTimeFormat date;
  private String reference;
  private int amount;
  @Enumerated(EnumType.STRING)
  private TransactionType dbcr;
}
