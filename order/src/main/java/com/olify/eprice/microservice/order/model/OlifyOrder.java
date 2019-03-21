/**
 * 
 */
package com.olify.eprice.microservice.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Olify
 *
 */
@Entity
@Table(name="olify_order")
public class OlifyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column(name="order_id")
	private Long orderId;
	@Embedded //embedding customer within an order class/table
	@ManyToOne
	@AttributeOverrides({@AttributeOverride(name = "name", column = @Column(name = "customer_name")),
						 @AttributeOverride(name = "mobile", column = @Column(name = "customer_mobile")),
						 @AttributeOverride(name = "email", column = @Column(name = "customer_email"))})
	private OlifyCustomer customer;
	@Embedded
	private OlifyMarket market;
	@Embedded
	private OlifyCategory category;
	@Embedded
	private OlifyProduct product;
	@Embedded
	private OlifyUser user;
	private int inventoryOrderTotal;
	private Date inventoryOrderDate;
	@Embedded
	private Address orderAddress;
	private Date inventoryRequiredDate;
	private String productDetails;
	private String paymentStatus;
	private String inventoryOrderStatus;
	@OneToMany(cascade = CascadeType.ALL)
	private List<OlifyOrderLine> orderLine;
	
	public OlifyOrder() {
		super();
	}
	
	public OlifyOrder(OlifyCustomer customer) {
		super();
		this.customer = customer;
		this.orderLine = new ArrayList<OlifyOrderLine>();
	}
	
	public OlifyOrder(Long orderId, OlifyCustomer customer, OlifyMarket market, OlifyCategory category,Address orderAddress,
			OlifyProduct product, OlifyUser user, int inventoryOrderTotal, Date inventoryOrderDate, String productDetails,
			String paymentStatus, String inventoryOrderStatus, List<OlifyOrderLine> orderLine) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.market = market;
		this.category = category;
		this.orderAddress = orderAddress;
		this.product = product;
		this.user = user;
		this.inventoryOrderTotal = inventoryOrderTotal;
		this.inventoryOrderDate = inventoryOrderDate;
		this.paymentStatus = paymentStatus;
		this.productDetails = productDetails;
		this.inventoryOrderStatus = inventoryOrderStatus;
		this.orderLine = orderLine;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public OlifyCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(OlifyCustomer customer) {
		this.customer = customer;
	}
	public OlifyMarket getMarket() {
		return market;
	}
	public void setMarket(OlifyMarket market) {
		this.market = market;
	}
	public OlifyCategory getCategory() {
		return category;
	}
	public void setCategory(OlifyCategory category) {
		this.category = category;
	}
	public OlifyProduct getProduct() {
		return product;
	}
	public void setProduct(OlifyProduct product) {
		this.product = product;
	}
	public OlifyUser getUser() {
		return user;
	}
	public void setUser(OlifyUser user) {
		this.user = user;
	}
	public Integer getInventoryOrderTotal() {
		return inventoryOrderTotal;
	}
	public void setInventoryOrderTotal(Integer inventoryOrderTotal) {
		this.inventoryOrderTotal = inventoryOrderTotal;
	}
	public Date getInventoryOrderDate() {
		return inventoryOrderDate; 
	}
	public void setInventoryOrderDate(Date inventoryOrderDate) {
		this.inventoryOrderDate = inventoryOrderDate;
	}
	public Address orderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(Address orderAddress) {
		this.orderAddress = orderAddress;
	}
	public Date getInventoryRequiredDate() {
		return inventoryRequiredDate;
	}
	public void setInventoryRequiredDate(Date inventoryRequiredDate) {
		this.inventoryRequiredDate = inventoryRequiredDate;
	}
	public String getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getInventoryOrderStatus() {
		return inventoryOrderStatus;
	}
	public void setInventoryOrderStatus(String inventoryOrderStatus) {
		this.inventoryOrderStatus = inventoryOrderStatus;
	}
	
	public int getNumberOfLines() {
		return orderLine.size();
	}

	public double totalPrice() {
		return orderLine.stream().map((ol) -> ol.getCount() * ol.getProduct().getPrice()).reduce(0.0, (d1, d2) -> d1 + d2);
	}
	
	public List<OlifyOrderLine> getOrderLine() {
		return orderLine;
	}
	public void setOrderLine(List<OlifyOrderLine> orderLine) {
		this.orderLine = orderLine;
	}
	
	public void addLine(int count, OlifyProduct product) {
		this.orderLine.add(new OlifyOrderLine(count, product));
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}}