package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String fullname;
	@NotBlank
	private String password;
	@NotEmpty
	private Set<String> roles;
}
