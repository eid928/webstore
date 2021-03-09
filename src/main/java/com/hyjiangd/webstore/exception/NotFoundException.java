package com.hyjiangd.webstore.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}
}
