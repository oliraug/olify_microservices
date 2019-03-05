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
@Table(name="olify_customers")
@EntityListeners(AuditingEntityListener.class)
public class OlifyCustomer{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="customer_mobile")
	private String customerMobile;
	@Column(name="customer_email")
	@Email
	private String customerEmail;
	@Column(name="customer_address")
	private String customerAddress;
	@Column(name="customer_status")
	private String customerStatus;
	@Column(name="customer_join_date")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date customerJoinDate;
	private int port;
	
	public OlifyCustomer() {
		
	}
	public OlifyCustomer(Long customerId, String customerName, String customerMobile, String customerEmail, String customerAddress, String customerStatus, Date customerJoinDate, int port) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerStatus = customerStatus;
		this.port = port;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public Date getCustomerJoinDate() {
		return customerJoinDate;
	}
	public void setCustomerJoinDate(Date customerJoinDate) {
		this.customerJoinDate = customerJoinDate;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	//method to copy customer with new customer Id
	public OlifyCustomer copyWithCustomerId(final Long CustomerId) {
		return new OlifyCustomer(customerId,this.customerName, this.customerMobile, this.customerEmail, 
				this.customerAddress, this.customerStatus, this.customerJoinDate, this.port);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(customerId);
		builder.append(customerName);
		builder.append(customerMobile);
		builder.append(customerEmail);
		builder.append(customerAddress);
		builder.append(customerStatus);
		builder.append(customerJoinDate);
		builder.append(port);
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	public boolean hasEmail() {
		return true;
	}
}