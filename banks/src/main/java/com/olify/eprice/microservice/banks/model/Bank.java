/**
 * 
 */
package com.olify.eprice.microservice.banks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Olify
 *
 */
@Entity
@Table(name="olify_banks", catalog="olify_banks")
@EntityListeners(AuditingEntityListener.class)
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="id", unique=true, nullable=false)
	private int id;
	@Column(name="bank_name", nullable=false)
	private String bankName;
	@Column(name="bank_code", nullable=false)
	private String bankCode;
	@Column(name="bank_street", nullable=false)
	private String bankStreet;
	@Column(name="bank_zip", nullable=false)
	private String bankZip;
	@Column(name="bank_city", nullable=false)
	private String bankCity;
	@Column(name="bank_state", nullable=false)
	private String bankState;
	@Column(name="bank_phone", nullable=false)
	private String bankPhone;
	@Column(name="bank_fax", nullable=false)
	private String bankFax;
	@Column(name="bank_email", nullable=false)
	@Email
	private String bankEmail;
	@Column(name="bank_country", nullable=false)
	private String bankCountry;
	@Column(name="bank_status", nullable=false)
	private String bankStatus;

	public Bank() {
		super();
	}
	public Bank(String bankName, String bankCode, String bankStreet, String bankZip, String bankCity, 
			String bankState, String bankPhone, String bankFax, String bankEmail, String bankCountry, String bankStatus) {
		super();
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.bankStreet = bankStreet;
		this.bankZip = bankZip;
		this.bankCity = bankCity;
		this.bankState = bankState;
		this.bankPhone = bankPhone;
		this.bankFax = bankFax;
		this.bankEmail = bankEmail;
		this.bankCountry = bankCountry;
		this.bankStatus = bankStatus;
	}

	public Bank(String bankName) {
		this.setBankName(bankName);
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getId() {
		return id;
	}

	public String getBankCode() {
		return bankCode;
	}

	public String getBankStreet() {
		return bankStreet;
	}

	public String getBankZip() {
		return bankZip;
	}

	public String getBankCity() {
		return bankCity;
	}

	public String getBankState() {
		return bankState;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public String getBankFax() {
		return bankFax;
	}

	public String getBankEmail() {
		return bankEmail;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setBankStreet(String bankStreet) {
		this.bankStreet = bankStreet;
	}

	public void setBankZip(String bankZip) {
		this.bankZip = bankZip;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public void setBankState(String bankState) {
		this.bankState = bankState;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public void setBankFax(String bankFax) {
		this.bankFax = bankFax;
	}

	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	@Override
	public String toString() {
		return "Bank [bankName=" + bankName +","
					+ "bankCode=" + bankCode +","
					+ "bankStreet=" + bankStreet +","
					+ "bankZip=" + bankZip +","
					+ "bankCity=" + bankCity +","
					+ "bankState=" + bankState +","
					+ "bankPhone=" + bankPhone +","
					+ "bankFax=" + bankFax +","
					+ "bankEmail=" + bankEmail +","
					+ "bankCountry=" + bankCountry +","
					+ "bankStatus=" + bankStatus +","
					+ "id=" + id +"]";
	}
}
