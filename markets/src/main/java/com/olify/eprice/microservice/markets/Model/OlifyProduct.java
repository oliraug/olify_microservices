package com.olify.eprice.microservice.markets.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

import com.olify.eprice.microservice.markets.Enums.ProductIntention;
import com.olify.eprice.microservice.markets.Enums.ProductMeasures;
import com.olify.eprice.microservice.markets.Enums.ProductStatus;

@Embeddable
public class OlifyProduct {
	@Column(name="product_id")
	private Long productId;
	@Column(name="product_name")
	private String productName;
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
	@Enumerated(EnumType.STRING)
	@Column(name="product_measures")
	private ProductMeasures productMeasures;
	@Enumerated(EnumType.STRING)
	@Column(name="product_status")
	private ProductStatus productStatus;
	@Enumerated(EnumType.STRING)
	@Column(name="product_intention")
	private ProductIntention productIntention;
	@Column(name="created_date", insertable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdDate;
	@Column(name="entered_by")
	private String enteredBy;
	
	public OlifyProduct() {
		super();
	}
	
	public OlifyProduct(String productName, Double price, int units, int unitsInStock, int unitsOnOrder, int quantityPerUnit,
			ProductMeasures productMeasures, ProductStatus productStatus, ProductIntention productIntention, Date createdDate,
			String enteredBy) {
		this.productName = productName;
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
	
	public Long getproductId() {
		return productId;
	}
	public void setproductId(Long productId) {
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
	public ProductStatus getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(ProductStatus productStatus) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(productId).append(productName).append(price).append(units).append(unitsInStock).append(unitsOnOrder)
				.append(quantityPerUnit).append(productMeasures).append(productStatus).append(productIntention)
				.append(createdDate).append(enteredBy);
		return builder.toString();
	}
}