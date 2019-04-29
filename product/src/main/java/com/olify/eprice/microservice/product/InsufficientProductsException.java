package com.olify.eprice.microservice.product;

public class InsufficientProductsException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = null;
	
	public InsufficientProductsException() {
		super();
	}

	public InsufficientProductsException(String message) {
		super(message);
		this.message = message;
	}

	public InsufficientProductsException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
			builder.append(message);
		return builder.toString();
	}
}