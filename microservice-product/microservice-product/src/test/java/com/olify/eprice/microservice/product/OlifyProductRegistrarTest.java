package com.olify.eprice.microservice.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.component.OlifyProductRegistrar;
import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.repository.OlifyProductRepository;

public class OlifyProductRegistrarTest {
	private OlifyProductRepository mockProductRepo;
	private OlifyProduct product;
	private OlifyProductRegistrar mockProductReg;

	@Before
	public void setUp() throws Exception {
		mockProductRepo = mock(OlifyProductRepository.class);
		mockProductReg = mock(OlifyProductRegistrar.class);
	}

	@Test
	public void test_shouldReturnProductWhenGetProductByIdIsCalled() throws Exception {
		//Arrange
		when(mockProductRepo.getOne(1L)).thenReturn(product);
		
		//act
		OlifyProduct retrievedProduct = mockProductReg.getProductById(1L);
		
		//assert
		assertThat(retrievedProduct).isEqualTo(product);
		verify(mockProductReg, atLeastOnce()).getProductById(1L);
	}
	
	@Test
	public void test_shouldReturnProductWhenSaveProductIsCalled() throws Exception {
		//Arrange
		when(mockProductRepo.save(product)).thenReturn(product);
		
		//act
		OlifyProduct savedProduct = mockProductReg.saveProduct(product);
		
		//assert
		assertThat(savedProduct).isEqualTo(product);
		verify(mockProductReg, atLeastOnce()).saveProduct(product);
	}
	
	@Test
	public void test_shouldCallDeleteMethodOfProductRegistrarWhenDeleteProductIsCalled() throws Exception{
		//Arrange
		doNothing().when(mockProductRepo).delete(product);
		
		//Act
		mockProductReg.deleteProduct(1L);
		
		//Assert
		verify(mockProductReg, times(1)).deleteProduct(1L);
	}

}
