package com.hyjiangd.webstore.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> notFoundExceptionHandle(NotFoundException exc) {
		
		ExceptionMessage error = new ExceptionMessage();
		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getLocalizedMessage());
		
		ResponseEntity<ExceptionMessage> responseEntity = new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> alreadyExistExceptionHandle(AlreadyExistException exc) {
		
		ExceptionMessage error = new ExceptionMessage();
		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getLocalizedMessage());
		error.setErrors(new HashMap<>());
		error.getErrors().put("username", exc.getLocalizedMessage());
		
		ResponseEntity<ExceptionMessage> responseEntity = new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> validationExceptionHandle(MethodArgumentNotValidException exc) {
		
		ExceptionMessage error = new ExceptionMessage();
		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getLocalizedMessage());
		error.setErrors(new HashMap<>());
		
		for (int i = 0; i < exc.getFieldErrors().size(); i ++) {
			System.out.println("field: " + exc.getFieldErrors().get(i).getField());
			System.out.println("error: " + exc.getFieldErrors().get(i).getDefaultMessage());
			error.getErrors().put(exc.getFieldErrors().get(i).getField(), exc.getFieldErrors().get(i).getDefaultMessage());
		}
		
		ResponseEntity<ExceptionMessage> responseEntity = new ResponseEntity<ExceptionMessage>(error, HttpStatus.BAD_REQUEST);
		
		return responseEntity;
	}
}
