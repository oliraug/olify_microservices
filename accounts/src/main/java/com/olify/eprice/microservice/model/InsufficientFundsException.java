package com.olify.eprice.microservice.model;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = -3372330225986261663L;
	private String text;
	
	public InsufficientFundsException(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
