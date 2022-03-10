package com.learning.entity;
import java.time.LocalDate;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(exclude={"accounts"})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="customerId") })
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	@NotBlank
	private String fullname;
	@NotBlank
	private String username;
	private String password;
	private String phone;
	@NotBlank
	private String pan;
	@NotBlank
	private String aadhar;
	private String secretQuestion;
	private String secretAnswer;
	//pan and aadhar are images
	//@JsonFormat(pattern = "yyyy-MM-dd")
//	private LocalDate doj = LocalDate.now();
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<com.learning.entity.Account> accounts;

	
}// customer has a profile
