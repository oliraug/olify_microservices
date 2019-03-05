/**
 * 
 */
package com.olify.eprice.microservice.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olify.eprice.microservice.model.OlifyProduct;

/**
 * @author olify
 *ProductRepository interface
 */
@RepositoryRestResource(collectionResourceRel = "olify_product", path = "olify_product")
public interface OlifyProductRepository extends JpaRepository<OlifyProduct, Long>{
	@Query("SELECT max(op.updated) FROM olify_product op")
	Date lastUpdate();

	int getAvailableProducts(OlifyProduct product); // method returns the number of available quantity of a Product passed to it
	int orderProduct(OlifyProduct product, int orderedQuantity); //places an order for product
}