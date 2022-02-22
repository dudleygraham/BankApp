package com.learning.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table
@ToString(exclude="user")
public class Address {
	public Address() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Integer hno;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private long zip;
	@ManyToOne
	private User user;
	
}
