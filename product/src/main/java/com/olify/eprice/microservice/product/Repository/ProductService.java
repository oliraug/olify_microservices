/**
 * 
 */
package com.olify.eprice.microservice.product.Repository;

import com.olify.eprice.microservice.product.Model.Product;

/**
 * @author Olify
 *
 */

public interface ProductService {
	//int findByAvailableProducts(Product product); // method returns the number of available quantity of a Product passed to it
	//int findByUnitsOnOrder(Product product, int unitsOnOrder); //places an order for product

	Product getOne(long id);
	/*void delete(Product product);
	Product save(Product product);
	//boolean buy(Product product, int purchaseQuantity);*/
}