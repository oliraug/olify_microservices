package com.olify.eprice.microservice.invoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="olify_invoice")
public class OlifyInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="id")
	private Long id;
	@Embedded
	@Column(name="customer")
	private OlifyCustomer customer;
	@Column(name="updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Embedded
	@Column(name="billingAddress")
	private Address billingAddress = new Address();
	@Embedded
	@Column(name="product")
	private OlifyProduct product;
	@Column(name="invoice_no")
	private int invoiceNo;
	@Embedded
	@Column(name="category")
	private OlifyCategory category;
	@Column(name="description")
	private String description;
	@Column(name="quantity")
	private int quantity;
	@Column(name="invoice_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	@Column(name="unit_cost")
	private double unitCost;
	@Column(name="sub_total")
	private double subTotal;
	@Column(name="vat")
	private int vat;
	@Column(name="total")
	private double total;
	@Column(name="payment_terms")
	private String paymentTerms;

	public OlifyInvoice() {
		super();
	}

	public OlifyInvoice(long id, OlifyCustomer customer, Date updated, Address billingAddress, OlifyProduct product) {
		super();
		this.id = id;
		this.customer = customer;
		this.updated = updated;
		this.billingAddress = billingAddress;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public OlifyCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(OlifyCustomer customer) {
		this.customer = customer;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public OlifyProduct getProduct() {
		return product;
	}

	public void setProduct(OlifyProduct product) {
		this.product = product;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public OlifyCategory getCategory() {
		return category;
	}

	public void setCategory(OlifyCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
}