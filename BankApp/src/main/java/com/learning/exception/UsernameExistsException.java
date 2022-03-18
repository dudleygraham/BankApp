package com.learning.exception;

import java.util.Arrays;

public class UsernameExistsException extends RuntimeException {
	public UsernameExistsException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
	
}