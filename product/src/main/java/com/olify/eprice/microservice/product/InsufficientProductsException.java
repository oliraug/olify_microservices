package com.olify.eprice.microservice.product;

public class InsufficientProductsException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InsufficientProductsException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}