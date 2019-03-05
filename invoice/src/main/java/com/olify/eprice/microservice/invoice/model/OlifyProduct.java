package com.olify.eprice.microservice.invoice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OlifyProduct {
	@Column(nullable = false)
	private Long productId;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private String name;

	public OlifyProduct() {
		super();
	}

	public OlifyProduct(String name, double price) {
		super();
		this.setName(name);
		this.setPrice(price);
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