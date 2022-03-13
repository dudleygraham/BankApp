package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="transferId") })
@ToString
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
