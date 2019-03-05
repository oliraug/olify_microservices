/**
 * 
 */
package com.olify.eprice.microservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Olify
 *
 */
@Entity
@Table(name="olify_user", catalog="olify_users")
@EntityListeners(AuditingEntityListener.class)
public class OlifyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="user_id")
	private Long id;
	@NotBlank
	@Column(name="name")
	private String name;
	@Column(name="email")
	@Email
	@NotBlank
	private String email;
	@Column(name="password")
	@NotBlank
	private String password;
	@Column(name="phone_no")
	@NotBlank
	private String phoneNo;
	@Column(name="sex")
	@NotBlank
	private String sex;
	@Column(name="speciality")
	@NotBlank
	private String speciality;
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdAt;
	
	public OlifyUser() {
		super();
	}

	public OlifyUser(Long id, String name, String email, String password, String phoneNo, String sex, String speciality, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.sex = sex;
		this.speciality = speciality;
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "OlifyUser [name="+ name +","
						   + "email="+ email +","
						   + "password="+ password +","
						   + "phoneNo="+ phoneNo +","
						   + "sex="+ sex +","
						   + "speciality="+ speciality +","
						   + "createdAt="+ createdAt +"]";
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}