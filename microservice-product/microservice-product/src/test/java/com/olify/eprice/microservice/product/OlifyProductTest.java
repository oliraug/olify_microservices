/**
 * 
 */
package com.olify.eprice.microservice.product;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.model.OlifyProduct;

/**
 * @author Olify
 *
 */
public class OlifyProductTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	private OlifyProduct createTestProductOne() {
		long productId = 1L;
		String productName = "Mangoes, Oranges, Pineapples";
		int units = 120;
		Double price = 2000.00;
		int unitsInStock = 120;
		int unitsOnOrder = 35;
		int quantityPerUnit = 15;
		String productMeasures = "Kgs";
		String productStatus = "active";
		String productIntention = "Selling";
		Date createdDate = new Date(12/12/2018);
		String enteredBy = "Moses Masiga";
		
		return new OlifyProduct(productId, productName, units, price, unitsInStock, unitsOnOrder, quantityPerUnit, productMeasures, productStatus,
				productIntention, createdDate, enteredBy);
	}

	@Test
	public void testProductShouldFillInAllParameters() throws Exception {
		long productId = 1L;
		String productName = "Mangoes, Oranges, Pineapples";
		int units = 120;
		Double price = 2000.00;
		int unitsInStock = 120;
		int unitsOnOrder = 35;
		int quantityPerUnit = 15;
		String productMeasures = "Kgs";
		String productStatus = "active";
		String productIntention = "Selling";
		Date createdDate = new Date(12/12/2018);
		String enteredBy = "Moses Masiga";
		
		OlifyProduct toProduct = new OlifyProduct(productId, productName, units, price, unitsInStock, unitsOnOrder, quantityPerUnit, productMeasures, productStatus,
				productIntention, createdDate, enteredBy);
		assertEquals((long) toProduct.getProductId(), 1L);
		assertThat(toProduct.getProductName()).isEqualTo(productName);
		assertThat(toProduct.getUnits()).isEqualTo(units);
		assertThat(toProduct.getPrice()).isEqualTo(price);
		assertThat(toProduct.getUnitsInStock()).isEqualTo(unitsInStock);
		assertThat(toProduct.getUnitsOnOrder()).isEqualTo(unitsOnOrder);
		assertThat(toProduct.getQuantityPerUnit()).isEqualTo(quantityPerUnit);
		assertThat(toProduct.getProductMeasures()).isEqualTo(productMeasures);
		assertThat(toProduct.getProductStatus()).isEqualTo(productStatus);
		assertThat(toProduct.getProductIntention()).isEqualTo(productIntention);
		assertThat(toProduct.getCreatedDate()).isEqualTo(createdDate);
		assertThat(toProduct.getEnteredBy()).isEqualTo(enteredBy);
	}
	
	/**
	   * @see Product#setProductNames(String)
	   */
	@Test
	public void testSetProduct_ShouldSetMultipleProductNames() throws Exception {
		OlifyProduct testProduct = createTestProductOne();
		String productNames = "Mangoes, Oranges, Apples, Grapes, Soya beans, peanuts";
		testProduct.setProductName(productNames);
		assertThat(testProduct.getProductName()).isEqualTo(productNames);
	}
	
	/**
	   * @see Product#addProductName(String)
	   */
	@Test
	public void testAddProduct_ShouldAddNewProduct() throws Exception {
		OlifyProduct testProduct = createTestProductOne();
		String newProduct = "Maize";
		
		testProduct.setProductName(newProduct);
		assertThat(testProduct.getProductName()).isEqualTo(newProduct);
	}

}