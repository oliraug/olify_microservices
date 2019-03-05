/**
 * 
 */
package com.olify.eprice.microservice.product.test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.model.OlifyCustomer;

/**
 * @author Olify
 *
 */
public class OlifyCustomerTest {

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
	
	private OlifyCustomer createTestCustomerOne() {
		long customerId = 1L;
		String customerName = "Moses Masiga, Willingstone Sombi";
		String customerMobile = "+256773405024";
		String customerEmail = "emacsone@aol.com";
		String customerAddress = "Mutungo Bbina";
		String customerStatus = "active";
		Date customerJoinDate = new Date(12/12/2018);
		int port = 8585;
		
		return new OlifyCustomer(customerId, customerName, customerMobile, customerEmail, customerAddress, customerStatus, customerJoinDate, port);
	}

	@Test
	public void testCustomerShouldFillInAllParameters() throws Exception {
		long customerId = 1L;
		String customerName = "Moses Masiga, Willingstone Sombi";
		String customerMobile = "+256773405024";
		String customerEmail = "emacsone@aol.com";
		String customerAddress = "Mutungo Bbina";
		String customerStatus = "active";
		Date customerJoinDate = new Date(12/12/2018);
		int port = 8585;
		
		OlifyCustomer toCustomer = new OlifyCustomer(customerId, customerName, customerMobile, customerEmail, customerAddress, customerStatus, customerJoinDate, port);
		assertEquals((long) toCustomer.getCustomerId(), 1L);
		assertThat(toCustomer.getCustomerName()).isEqualTo(customerName);
		assertThat(toCustomer.getCustomerMobile()).isEqualTo(customerMobile);
		assertThat(toCustomer.getCustomerEmail()).isEqualTo(customerEmail);
		assertThat(toCustomer.getCustomerAddress()).isEqualTo(customerAddress);
		assertThat(toCustomer.getCustomerStatus()).isEqualTo(customerStatus);
		//assertThat(toCustomer.getCustomerJoinDate()).isEqualTo(customerJoinDate);
		assertThat(toCustomer.getPort()).isEqualTo(port);
	}
	
	/**
	   * @see Customer#setCustomerNames(String)
	   */
	@Test
	public void testSetCustomer_ShouldSetMultipleCustomerNames() throws Exception {
		OlifyCustomer testCustomer = createTestCustomerOne();
		String customerNames = "Moses Masiga, Mukama Gerald, Willingstone Sombi";
		testCustomer.setCustomerName(customerNames);
		assertThat(testCustomer.getCustomerName()).isEqualTo(customerNames);
	}
	
	/**
	   * @see Customer#addCustomerName(String)
	   */
	@Test
	public void testAddCustomer_ShouldAddNewCustomer() throws Exception {
		OlifyCustomer testCustomer = createTestCustomerOne();
		String newCustomer = "Ajuma Nabwera";
		
		testCustomer.setCustomerName(newCustomer);
		assertThat(testCustomer.getCustomerName()).isEqualTo(newCustomer);
	}

	/**
	   * @see Customer#hasEmail()
	   */
	@Test
	public void testHashEmail_ShouldReturnTrueIfThisCustomerHasAnEmail() throws Exception {
		OlifyCustomer testCustomer = createTestCustomerOne();
		
		assertTrue(testCustomer.hasEmail());
	}
}
