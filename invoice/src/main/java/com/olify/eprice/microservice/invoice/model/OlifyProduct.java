package com.olify.eprice.microservice.invoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

@Embeddable
public class OlifyProduct {
	@Column(nullable = false)
	private Long productId;
	@Column(nullable = false)
	private String name;
	@Column(name="price")
	private Double price;
	@Column(name="units")
	private int units;
	@Column(name="units_in_stock")
	private int unitsInStock;
	@Column(name="units_on_order")
	private int unitsOnOrder;
	@Column(name="quantity_per_unit")
	private int quantityPerUnit;
	@Column(name="product_measures")
	private String productMeasures;
	@Column(name="product_status")
	private String productStatus;
	@Column(name="product_intention")
	private String productIntention;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdDate;
	@Column(name="entered_by")
	private String enteredBy;

	public OlifyProduct() {
		super();
	}

	public OlifyProduct(Long productId, String name, Double price, int units, int unitsInStock, int unitsOnOrder,
			int quantityPerUnit, String productMeasures, String productStatus, String productIntention,
			Date createdDate, String enteredBy) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.units = units;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.quantityPerUnit = quantityPerUnit;
		this.productMeasures = productMeasures;
		this.productStatus = productStatus;
		this.productIntention = productIntention;
		this.createdDate = createdDate;
		this.enteredBy = enteredBy;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public int getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(int quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public String getProductMeasures() {
		return productMeasures;
	}

	public void setProductMeasures(String productMeasures) {
		this.productMeasures = productMeasures;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductIntention() {
		return productIntention;
	}

	public void setProductIntention(String productIntention) {
		this.productIntention = productIntention;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}