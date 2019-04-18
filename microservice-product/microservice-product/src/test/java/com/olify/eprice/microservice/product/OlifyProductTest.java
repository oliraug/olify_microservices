/**
 * 
 */
package com.olify.eprice.microservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.product.enums.ProductIntention;
import com.olify.eprice.microservice.product.enums.ProductMeasures;
import com.olify.eprice.microservice.product.enums.ProductStatus;

/**
 * @author Olify
 *
 */
public class OlifyProductTest {
	long id = 1L;
	String productName = "Mangoes, Oranges, Pineapples";
	int units = 120;
	Double price = 2000.00;
	int unitsInStock = 120;
	int unitsOnOrder = 35;
	int quantityPerUnit = 15;
	Date createdDate = new Date(12/12/2018);
	String enteredBy = "Moses Masiga";

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
		return new OlifyProduct(productName, units, price, unitsInStock, unitsOnOrder, quantityPerUnit, ProductMeasures.PIECES, ProductStatus.ACTIVE,
				ProductIntention.SELLING, createdDate, enteredBy);
	}

	@Test
	public void testProductShouldFillInAllParameters() throws Exception {		
		OlifyProduct toProduct = new OlifyProduct(productName, units, price, unitsInStock, unitsOnOrder, quantityPerUnit, ProductMeasures.PIECES, ProductStatus.ACTIVE,
				ProductIntention.SELLING, createdDate, enteredBy);
		//assertEquals((long) toProduct.getId(), 1L);
		assertThat(toProduct.getProductName()).isEqualTo(productName);
		assertThat(toProduct.getUnits()).isEqualTo(units);
		assertThat(toProduct.getPrice()).isEqualTo(price);
		assertThat(toProduct.getUnitsInStock()).isEqualTo(unitsInStock);
		assertThat(toProduct.getUnitsOnOrder()).isEqualTo(unitsOnOrder);
		assertThat(toProduct.getQuantityPerUnit()).isEqualTo(quantityPerUnit);
		assertThat(toProduct.getProductMeasures()).isEqualTo(ProductMeasures.PIECES);
		assertThat(toProduct.getProductStatus()).isEqualTo(ProductStatus.ACTIVE);
		assertThat(toProduct.getProductIntention()).isEqualTo(ProductIntention.SELLING);
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