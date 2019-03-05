package com.olify.eprice.microservice.markets.Model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.olify.eprice.microservice.markets.Enums.ProductIntention;
import com.olify.eprice.microservice.markets.Enums.ProductMeasures;

@Embeddable
public class OlifyProduct {
	private Long productId;
	private String productName;
	private Double price;
	private int units;
	private int unitsInStock;
	private int unitsOnOrder;
	private int quantityPerUnit;
	@Enumerated(EnumType.STRING)
	private ProductMeasures productMeasures;
	private String productStatus;
	@Enumerated(EnumType.STRING)
	private ProductIntention productIntention;
	private Date createdDate;
	private String enteredBy;
	
	public OlifyProduct() {
		super();
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public ProductMeasures getProductMeasures() {
		return productMeasures;
	}
	public void setProductMeasures(ProductMeasures productMeasures) {
		this.productMeasures = productMeasures;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public ProductIntention getProductIntention() {
		return productIntention;
	}
	public void setProductIntention(ProductIntention productIntention) {
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
}
