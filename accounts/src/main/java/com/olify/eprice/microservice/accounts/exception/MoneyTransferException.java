package com.olify.eprice.microservice.accounts.exception;

public class MoneyTransferException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message = null;
	
	public MoneyTransferException() {
		
	}
	
	public MoneyTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}