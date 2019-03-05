/**
 * 
 */
package com.olify.eprice.microservice.component;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.repository.OlifyProductRepository;

/**
 * @author Olify
 *
 */
@Component
public class ProductTestDataGenerator {
	private final OlifyProductRepository productRepository;

	@Autowired
	public ProductTestDataGenerator(OlifyProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostConstruct
	public void generateTestData() {
		productRepository
				.save(new OlifyProduct(1L,"Pineapples", 10, 3500.0, 125, 55, 35, "nos", "active", "selling", new Date(12/20/2018),"Masiga Moses"));
		productRepository.save(new OlifyProduct(2L,"Mangoes", 30, 2000.0, 100, 50, 25, "nos", "active", "buying", new Date(12/20/2018),"Masiga Moses"));
	}

}
