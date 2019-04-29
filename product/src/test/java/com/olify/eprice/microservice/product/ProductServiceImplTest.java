package com.olify.eprice.microservice.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.product.Component.ProductServiceImpl;
import com.olify.eprice.microservice.product.Model.Product;
import com.olify.eprice.microservice.product.Repository.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceImplTest {
	private ProductRepository mockProductRepo;
	private Product product;
	private ProductServiceImpl mockProductReg;
	private int purchaseQuantity = 15;

	@Before
	public void setUp() throws Exception {
		mockProductRepo = mock(ProductRepository.class);
		mockProductReg = mock(ProductServiceImpl.class);
	}

	@Test
	public void test_shouldReturnProductWhenGetProductByIdIsCalled() throws Exception {
		//Arrange
		when(mockProductRepo.getOne(1L)).thenReturn(product);
		
		//act
		Product retrievedProduct = mockProductReg.getProductById(1L);
		
		//assert
		assertThat(retrievedProduct).isEqualTo(product);
		verify(mockProductReg, atLeastOnce()).getProductById(1L);
	}
	
	@Test
	public void test_shouldReturnProductWhenSaveProductIsCalled() throws Exception {
		//Arrange
		when(mockProductRepo.save(product)).thenReturn(product);
		
		//act
		Product savedProduct = mockProductReg.saveProduct(product);
		
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

	@Test
	public void test_shouldBeAbleToBuyAProduct() throws InsufficientProductsException {
		int availableQuantity = 30;
		//stubbing getAvailableProducts() of productRepository to return 30
		when(mockProductRepo.findByUnitsInStock(product)).thenReturn(availableQuantity);
		
		//buy method of ProductServiceImpl() that's under test
		mockProductReg.buy(product, purchaseQuantity);
		
		//we confirm that the stubbing performed as expected
		assertThat(mockProductRepo.findByUnitsInStock(product)).isEqualTo(availableQuantity);		
		
		verify(mockProductRepo, atLeastOnce()).findByUnitsInStock(product);
		
		//used the inOrder() method to verify the order of method invocation that the buy() method makes on ProductServiceImpl
		InOrder order = inOrder(mockProductRepo);
		order.verify(mockProductRepo).findByUnitsInStock(product);
		//order.verify(mockProductRepo).orderProduct(product, purchaseQuantity);
	}
	
	@Test(expected=InsufficientProductsException.class)
	public void test_shouldPurchaseWithInsufficientAvailableQuantity() throws InsufficientProductsException {
		int quantityAvailable=3;
		
		when(mockProductReg.getAvailableProducts(product)).thenReturn(quantityAvailable);
		try {
			mockProductReg.buy(product, purchaseQuantity);
		} catch(InsufficientProductsException e) {
			//verify(mockProductReg, times(0)).findByProductNameAndUnitsOnOrder(product, purchaseQuantity);
			throw e;
		}
	}
	
}