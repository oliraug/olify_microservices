/**
 * 
 */
package com.olify.eprice.microservice.component;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.product.enums.ProductIntention;
import com.olify.eprice.microservice.product.enums.ProductMeasures;
import com.olify.eprice.microservice.product.enums.ProductStatus;
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
				.save(new OlifyProduct("Pineapples", 10, 3500.0, 125, 55, 35, ProductMeasures.PIECES, ProductStatus.ACTIVE, ProductIntention.SELLING, new Date(12/20/2018),"Masiga Moses"));
		productRepository.save(new OlifyProduct("Mangoes", 30, 2000.0, 100, 50, 25, ProductMeasures.PIECES, ProductStatus.ACTIVE, ProductIntention.SELLING, new Date(12/20/2018),"Masiga Moses"));
	}

}
