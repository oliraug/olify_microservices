package com.olify.eprice.microservice.invoice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Embeddable
public class OlifyCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="category_id")
	private Long id;
	@Column(name="category_name")
	@NotBlank
	private String categoryName;
	@Column(name="category_status")
	@NotBlank
	private String categoryStatus;
	@Column(name="description")
	private String description;
	

	public OlifyCategory() {
		
	}

	public OlifyCategory(@NotBlank String categoryName, @NotBlank String categoryStatus, String description) {
		super();
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
		this.description = description;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getCategoryStatus() {
		return categoryStatus;
	}


	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}