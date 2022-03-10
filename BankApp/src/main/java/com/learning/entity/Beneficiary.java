package com.learning.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.enums.ActiveType;

public class Beneficiary {
  private int beneficiaryAccountNo;
  private String beneficiaryName;
  @Enumerated(EnumType.STRING)
  private ActiveType active;
}
