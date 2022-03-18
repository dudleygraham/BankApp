package com.learning.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String fullname;
	@NotBlank
	private String password;

}
