/**
 * 
 */
package com.olify.eprice.microservice.product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.product.Model.Product;

/**
 * @author Olify
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByProductName(String productName);
}