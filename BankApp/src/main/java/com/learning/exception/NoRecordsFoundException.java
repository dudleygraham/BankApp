package com.learning.exception;


public class NoRecordsFoundException extends Exception {
	public NoRecordsFoundException() {
		super();
	}

	public NoRecordsFoundException(String message) {
		super(message);
	}
}
