package com.olify.eprice.microservice.markets.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.olify.eprice.microservice.markets.Enums.MarketStatus;

@Entity
@Table(name="olify_markets", catalog="olify_markets")
@EntityListeners(AuditingEntityListener.class)
public class Markets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(name="market_name")
	@NotBlank
	private String marketName;
	@Column(name="market_status", nullable=false)
	@Enumerated(EnumType.STRING)
	private MarketStatus marketStatus;
	@Column(name="user", nullable=false)
	@Embedded
	private User user;
	@Column(name="location")
	@NotBlank
	private String location;
	@Column(name="product", nullable=false)
	@Embedded
	private OlifyProduct product;
	@Column(name="country")
	@NotBlank
	private String country;
	@Column(name="created_date", insertable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdAt;
	@Column(name="updated_date", insertable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Markets() {
		super();
	}
	
	public Markets(String marketName,MarketStatus marketStatus, User user, String location, OlifyProduct product,
			String country, Date createdAt, Date updatedAt) {
		super();
		this.marketName = marketName;
		this.marketStatus = marketStatus;
		this.user = user;
		this.location = location;
		this.product = product;
		this.country = country;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public MarketStatus getMarketStatus() {
		return marketStatus;
	}

	public void setMarketStatus(MarketStatus marketStatus) {
		this.marketStatus = marketStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public OlifyProduct getProduct() {
		return product;
	}

	public void setProduct(OlifyProduct product) {
		this.product = product;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(marketName).append(user).append(product).append(location)
				.append(country).append(marketStatus).append(createdAt).append(updatedAt);
		return builder.toString();
	}
}