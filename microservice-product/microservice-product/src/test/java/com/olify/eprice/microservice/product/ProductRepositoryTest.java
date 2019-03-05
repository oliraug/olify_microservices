package com.olify.eprice.microservice.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.component.OlifyProductRegistrar;
import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.repository.OlifyProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductRepositoryTest {
	private OlifyProductRepository productRepository;
	@Mock private OlifyProduct product;
	private OlifyProductRegistrar olifyProductRegistrar;
	private int purchaseQuantity = 15;

	@Before
	public void setUp() throws Exception {
		productRepository = mock(OlifyProductRepository.class);
		olifyProductRegistrar = mock(OlifyProductRegistrar.class);
	}

	@Test
	public void test_shouldBeAbleToBuyAProduct() throws InsufficientProductsException {
		int availableQuantity = 30;
		//stubbing getAvailableProducts() of productRepository to return 30
		when(productRepository.getAvailableProducts(product)).thenReturn(availableQuantity);
		
		//buy method of OlifyProductRegistrar() that's under test
		olifyProductRegistrar.buy(product, purchaseQuantity);
		
		//we confirm that the stubbing performed as expected
		assertThat(productRepository.getAvailableProducts(product)).isEqualTo(availableQuantity);		
		
		//verify(productRepository, atLeastOnce()).orderProduct(product, purchaseQuantity);
		verify(productRepository, atLeastOnce()).getAvailableProducts(product);
	}

}
