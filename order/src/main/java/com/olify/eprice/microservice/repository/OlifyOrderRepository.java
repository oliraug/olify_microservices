package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olify.eprice.microservice.order.model.OlifyOrder;
import com.olify.eprice.microservice.order.model.OlifyProduct;

@RepositoryRestResource(collectionResourceRel = "olify_order", path = "olify_order")
public interface OlifyOrderRepository extends JpaRepository<OlifyOrder, Long>{
	int orderProduct(OlifyProduct product, int orderedQuantity); //places an order for product

}
