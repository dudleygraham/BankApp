package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.learning.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long staffId;
	@NotBlank
	private String staffFullName;
	@NotBlank
	private String staffUserName;
	@NotBlank
	private String staffPassword;
	@Enumerated(EnumType.STRING)
	private Status status; 
}
