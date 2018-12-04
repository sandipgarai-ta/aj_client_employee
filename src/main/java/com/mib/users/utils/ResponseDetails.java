package com.mib.users.utils;

public class ResponseDetails {
	private String message;
	private int status;
	public ResponseDetails() {
		super();
	}
	
	public ResponseDetails(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ResponseStatus [message=" + message + ", status=" + status + "]";
	}
	
}
