/**
 * 
 */
package com.olify.eprice.microservice.order.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Olify
 *@Embeddable means this class will not exist in the db as a separate table
 */
@Embeddable
public class OlifyProduct {

	/**
	 * 
	 */
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double price;
	
	public OlifyProduct() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
