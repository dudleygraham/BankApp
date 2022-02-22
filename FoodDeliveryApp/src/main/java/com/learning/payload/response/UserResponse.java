package com.learning.payload.response;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.payload.request.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfJoining;
	//@NotEmpty
	private Set<Address> address;
	@NotEmpty
	private Set<String> roles;
}
