/**
 * 
 */
package com.olify.eprice.microservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/*import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;*/
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Olify
 *
 */
@Entity
@Table(name="olify_accounts", catalog="accountsDB")
@EntityListeners(AuditingEntityListener.class)
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="account_id")
	private Long id;
	@NotBlank
	@Column(name="account_name")
	private String accountName;
	@Column(name="parent")
	private String parent;
	@NotBlank
	@Column(name="internal_type")
	private String internalType;
	@NotBlank
	@Column(name="account_type")
	private String accountType;
	@NotBlank
	@Column(name="debit")
	private double debit;
	@NotBlank
	@Column(name="credit")
	private double credit;
	@NotBlank
	@Column(name="balance")
	private double balance;
	@NotBlank
	@Column(name="account_status")
	private String accountStatus;
	@NotBlank
	@Column(name="default_taxes")
	private String defaultTaxes;
	@NotBlank
	@Column(name="reconcillation")
	private String reconcillation;
	@Column(name="notes")
	private String notes;
	
	
	public Accounts() {
		
	}
	public Accounts(String accountName, String parent, String internalType, String accountType, double debit, double credit,
			double balance, String accountStatus, String defaultTaxes, String reconcillation, String notes) {
		super();
		this.accountName = accountName;
		this.parent = parent;
		this.internalType = internalType;
		this.accountType = accountType;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
		this.accountStatus = accountStatus;
		this.defaultTaxes = defaultTaxes;
		this.reconcillation = reconcillation;
		this.notes = notes;
	}
	public Accounts(double amount) {
		// TODO Auto-generated constructor stub
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getInternalType() {
		return internalType;
	}
	public void setInternalType(String internalType) {
		this.internalType = internalType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getDefaultTaxes() {
		return defaultTaxes;
	}
	public void setDefaultTaxes(String defaultTaxes) {
		this.defaultTaxes = defaultTaxes;
	}
	public String getReconcillation() {
		return reconcillation;
	}
	public void setReconcillation(String reconcillation) {
		this.reconcillation = reconcillation;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(accountName);
		return builder.toString();
		//return ToStringBuilder.reflectionToString(this);
	}
	
	/*@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}*/
	public void deposit(double amount) {
		balance += amount;		
	}
	
	public void withdrawal(double amount) throws InsufficientFundsException {
		if(checkSufficientFunds(amount)) {
		balance -= amount;
	 } else {
		 throw new InsufficientFundsException("Insufficient funds!");
	 }
 }
	
	private boolean checkSufficientFunds(double amount) {
		if(balance >= amount)
			return true;
		else
			return false;
	}
	
	public void transfer(double amount, Accounts account) throws InsufficientFundsException {
		deposit(amount);
	}
}