/**
 * 
 */
package com.olify.eprice.microservice.product.Repository;

import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.product.Model.Product;

/**
 * @author Olify
 *
 */
@Repository
public interface ProductService {
	int getAvailableProducts(Product product); // method returns the number of available quantity of a Product passed to it
	int orderProduct(Product product, int orderedQuantity); //places an order for product

	Product getOne(String productName);
	Product getOne(long id);
	void delete(Product product);
	Product save(Product product);
}
