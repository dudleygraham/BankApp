package com.learning.exception.apierror;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ApiError {
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime localDateTime;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	private ApiError() {
		localDateTime=LocalDateTime.now();
	}
	public ApiError(HttpStatus s) {
		this();
		this.status=s;
	}
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	//create sub error for each and add it to list
	private void addSubError(ApiSubError ase) {
		if(subErrors==null) {
			subErrors = new ArrayList<>();
		}
		subErrors.add(ase);
	}
	private void addValidationError(String object,String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object,field,rejectedValue,message));
	}//creates object of type ApiValidationError
	private void addValidationError(String Object, String message) {
		addSubError(new ApiValidationError(Object,message));
	}
	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getObjectName(),fieldError.getField(),fieldError.getRejectedValue(),fieldError.getDefaultMessage());
	}
	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(e->this.addValidationError(e));
	}
	private void addValidationError(ObjectError objectError) {
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}
	public void addValidaitonObjectErrors(List<ObjectError> errors) {
		errors.forEach(e->this.addValidationError(e));
	}
	public void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(cv.getRootBeanClass().getName(), ((PathImpl)cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
	}
	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(e->addValidationError(e));
	}
}
