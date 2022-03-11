package com.learning.payload.response;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.EnableType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@Enumerated(EnumType.STRING)
	private EnableType enabled;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreated;
}
