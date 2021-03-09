package com.hyjiangd.webstore.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		ResponseEntity<ExceptionMessage> responseEntity = new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
	}
}
