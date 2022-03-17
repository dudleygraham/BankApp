package com.learning.entity;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.EnableType;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
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
	@NotBlank
	private String password;
	private String phone;
	
	private String pan;
	//private Object PANimg;
	private String aadhar;
	
	private String secretQuestion;
	private String secretAnswer;
	//pan and aadhar are images
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreated;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private EnableType enabled;
	@OneToMany(mappedBy = "customerId", cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Account> CustomerAccounts;
	
}