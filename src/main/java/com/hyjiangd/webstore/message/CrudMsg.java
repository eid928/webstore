package com.hyjiangd.webstore.message;

import java.util.Date;

public class CrudMsg {
	
	private String message;
	private Date now;
	
	public CrudMsg() {
		
	}

	public CrudMsg(String message, Date now) {
		this.message = message;
		this.now = now;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	@Override
	public String toString() {
		return "CrudMsg [message=" + message + ", now=" + now + "]";
	}
}
