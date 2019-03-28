package com.olify.eprice.microservice.invoice.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String zip;
	private String city;

	public Address() {
		super();
	}

	public Address(String street, String zip, String city) {
		super();
		this.setStreet(street);
		this.setZip(zip);
		this.setCity(city);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(street).append(city).append(zip);
		return builder.toString();
	}
}