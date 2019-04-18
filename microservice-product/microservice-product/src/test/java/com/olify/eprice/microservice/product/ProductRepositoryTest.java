package com.olify.eprice.microservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.product.enums.ProductIntention;
import com.olify.eprice.microservice.product.enums.ProductMeasures;
import com.olify.eprice.microservice.product.enums.ProductStatus;
import com.olify.eprice.microservice.repository.OlifyProductRepository;
import com.olify.eprice.microserviceproduct.MicroserviceProductApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=MicroserviceProductApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProductRepositoryTest {
	@Autowired 
	private OlifyProductRepository productRepository;
	@Autowired 
	private TestEntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After 
	public void tearDown() throws Exception {
		entityManager.clear();
		productRepository.flush();
	}

	@Test
	public void test_shouldFindNoProductsIfRepositoryIsEmpty() throws Exception {
		Iterable<OlifyProduct> product = productRepository.findAll();
		
		assertThat(product).isEmpty();
	}
	
	@Test
	public void test_whenSaveProductShouldReturnFindByProductName() throws Exception {
		productRepository.save(new OlifyProduct("Coffee", 10, 1500.00, 1000, 560, 145, ProductMeasures.KG, ProductStatus.ACTIVE, ProductIntention.SELLING, new Date(System.currentTimeMillis()), "Masiga"));		
		
		assertThat(productRepository.findByProductName("Coffee")).isNotNull();
	}

}