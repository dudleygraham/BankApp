package com.learning.advice;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.apierror.ApiError;
import com.learning.exception.NameAlreadyExistsException;
import com.learning.exception.NoDataFoundException;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundException(NoDataFoundException e){
		Map<String,String> m = new HashMap<>();
		m.put("message", "no data found");
		ApiError ae = new ApiError(HttpStatus.NOT_FOUND, "name already exists",e);
		return buildResponseEntity(ae);
	}
	@ExceptionHandler(NameAlreadyExistsException.class)
	public ResponseEntity<?> nameAlreadyExistsException(NameAlreadyExistsException e){
		Map<String,String> m = new HashMap<>();
		m.put("message", "name already exists");
		ApiError ae = new ApiError(HttpStatus.BAD_REQUEST, "name already exists",e);
		return buildResponseEntity(ae);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ApiError ae = new ApiError(HttpStatus.BAD_REQUEST);
		ae.setMessage("Validation Error");
		ae.addValidationErrors(ex.getFieldErrors());
		ae.addValidaitonObjectErrors(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(ae);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e){
		ApiError ae = new ApiError(HttpStatus.BAD_REQUEST);
		ae.setMessage(e.getMessage());
		ae.setDebugMessage(e.getRequiredType().getName());
		return buildResponseEntity(ae);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleMethodConstraintViolationException(ConstraintViolationException e){
		ApiError ae = new ApiError(HttpStatus.BAD_REQUEST);
		ae.setMessage(e.getMessage());
		return buildResponseEntity(ae);
	}
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleException(Exception e){
		ApiError ae = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		ae.setMessage(e.getMessage());
		return buildResponseEntity(ae);
	}
}
