package com.hyjiangd.webstore.exception;

import java.util.Date;
import java.util.Map;

public class ExceptionMessage {
	
	private int status;
	private Date timestamp;
	private String message;
	private Map<String, String> errors;
	
	public ExceptionMessage() {
		
	}

	public ExceptionMessage(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
