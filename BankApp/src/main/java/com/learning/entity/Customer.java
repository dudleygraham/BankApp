package com.learning.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.payload.request.AccountRequest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(exclude={"accounts"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="customerId") })
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	@NotBlank
	private String username;
	@NotBlank
	private String fullname;
	@NotBlank
	private String password;
	@NotNull
	private String phone;
	@NotBlank
	private String pan;
	@NotBlank
	private String aadhar;
	@NotBlank
	private String secretQuestion;
	@NotBlank
	private String secretAnswer;
	@JsonIgnore
	@OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> customerAccounts;
//	@OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "customerId")
//	private Set<Beneficiary> beneficiaries;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_roles",
//	joinColumns = @JoinColumn(name = "user_id"),
//	inverseJoinColumns = @JoinColumn(name = "id"))
//	private Set<Role> roles = new HashSet<>();
	
}
