/**
 * 
 */
package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.model.OlifyProduct;

/**
 * @author olify
 *ProductRepository interface
 */
@Repository
public interface OlifyProductRepository extends JpaRepository<OlifyProduct, Long>{
	int getAvailableProducts(OlifyProduct product); // method returns the number of available quantity of a Product passed to it
	int orderProduct(OlifyProduct product, int orderedQuantity); //places an order for product

	OlifyProduct getOne(String productName);
	OlifyProduct findByProductName(String productName);
}