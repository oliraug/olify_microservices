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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author olify
 * Java class for handling products data
 */
@Entity
@Table(name="olify_product", catalog="olify_products")
@EntityListeners(AuditingEntityListener.class)
public class OlifyProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="product_id")
	private Long id;
	@Column(name="product_name", nullable=false)
	private String productName;
	@Column(name="price", nullable=false)
	private Double price;
	@Column(name="units", nullable=false)
	private int units;
	@Column(name="units_in_stock", nullable=false)
	private int unitsInStock;
	@Column(name="units_on_order", nullable=false)
	private int unitsOnOrder;
	@Column(name="quantity_per_unit", nullable=false)
	private int quantityPerUnit;
	@Column(name="product_measures", nullable=false)
	private String productMeasures;
	@Column(name="product_status", nullable=false)
	private String productStatus;
	@Column(name="product_intention", nullable=false)
	private String productIntention;
	@Column(name="created_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdDate;
	@Column(name="entered_by", nullable=false)
	private String enteredBy;

	public OlifyProduct() {
	
	}

	public OlifyProduct(Long id, String productName, int units, Double price, int unitsInStock, int unitsOnOrder,
			int quantityPerUnit, String productMeasures, String productStatus, String productIntention, Date createdDate, String enteredBy) {
		super();
		this.id = id;
		this.productName = productName;
		this.units = units;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.quantityPerUnit = quantityPerUnit;
		this.productMeasures = productMeasures;
		this.productStatus = productStatus;
		this.productIntention = productIntention;
		this.createdDate = createdDate;
		this.enteredBy = enteredBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append(productName);
		builder.append(units);
		builder.append(price);
		builder.append(unitsInStock);
		builder.append(unitsOnOrder);
		builder.append(quantityPerUnit);
		builder.append(productIntention);
		builder.append(productMeasures);
		builder.append(productStatus);
		builder.append(createdDate);
		builder.append(enteredBy);
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
}