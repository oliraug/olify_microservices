/**
 * 
 */
package com.olify.eprice.microservice.product.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;
import com.olify.eprice.microservicecustomer.MicroserviceCustomerApplication;

/**
 * @author Olify
 *
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=MicroserviceCustomerApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class OlifyCustomerRepositoryTest {
/*
 * Use entityManager to create two new rows of data in the Arrival table of test H2 database
 */
	@Autowired private TestEntityManager entityManager;
	@Autowired private OlifyCustomerRepository olifyCustomerRepository;
	   
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
		entityManager.clear();
		olifyCustomerRepository.flush();
	}

	/*
	 * Search for all records from the database via findAll query,
	 * Perform assertions on the size of the data and the equality of the gathered objects
	 */
	@Test
	public void testWhenFindAll() throws Exception {
		//given
		OlifyCustomer firstCustomer = new OlifyCustomer();
		firstCustomer.setCustomerName("Moses Masiga");
		entityManager.persistAndFlush(firstCustomer);
		
		OlifyCustomer secondCustomer = new OlifyCustomer();
		secondCustomer.setCustomerName("Willings Sombi");
		entityManager.persistAndFlush(secondCustomer);
		
		//when
		List<OlifyCustomer> customers = olifyCustomerRepository.findAll();
		
		//then
		assertThat(customers.size()).isEqualTo(0);
		assertThat(customers.add(firstCustomer)).isEqualTo(true);
		assertThat(customers.add(secondCustomer)).isEqualTo(true);		
	}
	
	/*
	 * Search for all records from the database via findAllById query 
	 */
	@Test
	public void testWhenFindAllById() throws Exception {
		//given
		OlifyCustomer customer = new OlifyCustomer();
		customer.setCustomerName("Moses Masiga");
		entityManager.persistAndFlush(customer);

		//when
		Iterable<OlifyCustomer> testCustomer = olifyCustomerRepository.findAll();
		//then
		assertThat(testCustomer).hasSize(1).contains(customer);		
	}
	
	@Test
	public void test_shouldFindNoCustomersIfRepositoryIsEmpty() throws Exception {
		Iterable<OlifyCustomer> customer = olifyCustomerRepository.findAll();
		
		assertThat(customer).isEmpty();
	}

}
