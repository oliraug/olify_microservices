package com.olify.eprice.microservice.markets.Model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import com.olify.eprice.microservice.markets.Enums.Speciality;

import javax.persistence.EnumType;

@Embeddable
public class User {
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private String sex;
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	private Date createdAt;
	
	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
