package com.olify.eprice.microservice.invoice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
public class OlifyCustomer {
	@Column(nullable = false)
	private Long customerId;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	@Email
	private String email;
	
	@Column(nullable = false)
	private String phoneNo;

	public OlifyCustomer() {
		super();
	}

	public OlifyCustomer(Long customerId, String firstname, String lastname, String email, String phoneNo) {
		super();
		this.customerId = customerId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}