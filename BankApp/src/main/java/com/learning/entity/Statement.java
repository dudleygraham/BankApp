package com.learning.entity;

import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

public class Statement {
  @NotNull
  private int balance;
  @OneToOne
  private Account acct;
  @OneToMany
  private List<Transaction> transactions;
}
