package com.olify.eprice.microservice.product.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.olify.eprice.microservice.product.Enums.ProductIntention;
import com.olify.eprice.microservice.product.Enums.ProductMeasures;
import com.olify.eprice.microservice.product.Enums.ProductStatus;

@Entity
@Table(name="product", catalog="products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
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
	@Enumerated(EnumType.STRING)
	private ProductMeasures productMeasures;
	@Column(name="product_status", nullable=false)
	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;
	@Column(name="product_intention", nullable=false)
	@Enumerated(EnumType.STRING)
	private ProductIntention productIntention;
	@Column(name="created_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdDate;
	@Column(name="entered_by", nullable=false)
	private String enteredBy;
	

	public Product() {	
	}

	public Product(String productName, int units, Double price, int unitsInStock, int unitsOnOrder,
			int quantityPerUnit, ProductMeasures productMeasures, ProductStatus productStatus, ProductIntention productIntention, Date createdDate, String enteredBy) {
		super();
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

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		builder.append(id).append(productName).append(units).append(price).append(unitsInStock)
		.append(unitsOnOrder).append(quantityPerUnit).append(productIntention).append(productMeasures).append(productStatus).append(createdDate).append(enteredBy);
		return builder.toString();		
	}
}