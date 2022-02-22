package com.learning;

public class IdNotFoundException extends RuntimeException {
	public IdNotFoundException(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}
