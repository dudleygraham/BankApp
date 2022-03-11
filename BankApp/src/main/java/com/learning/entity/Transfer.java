package com.learning.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="transferId") })
public class Transfer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transferId;
  private long fromAccNumber;
  private long toAccNumber;
  private int amount;
  private String reason;
  private String by;
}
