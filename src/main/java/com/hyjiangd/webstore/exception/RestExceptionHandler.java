package com.hyjiangd.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> exceptionHandle(RuntimeException exc) {
		
		ExceptionMessage error = new ExceptionMessage();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getLocalizedMessage());
		
		ResponseEntity<ExceptionMessage> responseEntity = new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
}
