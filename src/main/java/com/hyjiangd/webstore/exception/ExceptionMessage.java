package com.hyjiangd.webstore.exception;

public class ExceptionMessage {
	
	private int status;
	private String message;
	
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
}
