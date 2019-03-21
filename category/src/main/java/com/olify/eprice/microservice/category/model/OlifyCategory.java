/**
 * 
 */
package com.olify.eprice.microservice.category.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Olify
 *
 */
@Entity
@Table(name ="olify_category")
@EntityListeners(AuditingEntityListener.class)
public class OlifyCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="category_id")
	private Long id;
	@Embedded
	@Column(name="user_id")
	private OlifyUser user;
	@Column(name="category_name")
	@NotBlank
	private String categoryName;
	@Column(name="category_status")
	@NotBlank
	private String categoryStatus;
	@Column(name="description")
	private String description;
	

	public OlifyCategory() {
		super();
	}
	
	public OlifyCategory(OlifyUser user, String categoryName, String categoryStatus, String description) {
		super();
		this.user = user;
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

	public OlifyUser getUser() {
		return user;
	}

	public void setUser(OlifyUser user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "OlifyCategory [categoryName=" + categoryName + ", categoryStatus="
				+ categoryStatus + ", description=" + description
				+ ", user=" + user
				+ ", id=" + id + "]";
	}
}