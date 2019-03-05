/**
 * Application configuration using spring framework
 */
package com.olify.eprice.microservice.product.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.controller.OlifyCustomerController;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservicecustomer.MicroserviceCustomerApplication;

/**
 * @author Olify
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // Executes the test with spring runner
@SpringBootTest(classes=MicroserviceCustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK) // Loads application context via spring boot
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class}) //needed to inject dependencies into my test e.g (@Autowired, @Inject, @Mock, @Resource etc)
@WebAppConfiguration // Enables web context testing

public class OlifyCustomerControllerTest{
	@InjectMocks
	private OlifyCustomerController mockCustomerController;
	@Autowired 
	@Mock
	private OlifyCustomerRegistrar mockCustomerRegistrar;
	private MockMvc mockMvc;
	
	@Mock private OlifyCustomer mockCustomer;
	@Autowired 
	private WebApplicationContext wac;   //Injects WebApplicationContext

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();  //Sets up MockMvc
		mockCustomerRegistrar = mock(OlifyCustomerRegistrar.class, Mockito.RETURNS_DEEP_STUBS);
		mockCustomerController = mock(OlifyCustomerController.class, Mockito.RETURNS_DEEP_STUBS);
	}

	@Test
	public void testCreateCustomerReturnsSuccess() throws Exception {
		long customerId = 1L;
		String customerName = "Moses Masiga, Willingstone Sombi";
		String customerMobile = "+256773405024";
		String customerEmail = "emacsone@aol.com";
		String customerAddress = "Mutungo Bbina";
		String customerStatus = "active";
		Date customerJoinDate = new Date(12/12/2018);
		int port = 8585;
		
		new OlifyCustomer(customerId, customerName, customerMobile, customerEmail, customerAddress, customerStatus, customerJoinDate, port);
		
		Mockito.when(mockCustomerRegistrar.createCustomer(mockCustomer)).thenReturn(mockCustomer);
		this.mockMvc.perform(post("/olifycustomer")  //Performs a POST request
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockCustomer)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("location", anything("http://localhost:8080/")))
				.andDo(print());
		assertThat(mockCustomerRegistrar.createCustomer(mockCustomer)).isEqualTo(mockCustomer);
		Mockito.verify(mockCustomerRegistrar, times(1)).createCustomer(mockCustomer);
		Mockito.verifyNoMoreInteractions(mockCustomerRegistrar);
	}
	
	public static String asJsonString(final Object obj) {
		try{
			return new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writeValueAsString(obj);
			//return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY).writeValueAsString(obj);
		} catch (Exception e){
		 throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testPostCustomer() throws Exception {
		//Performs a POST request
		mockMvc.perform(post("/olifycustomers")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("customerName", "Moses Masiga")
				.param("customerMobile", "0704008863")
				.param("customerEmail", "emacsone@aol.com")
				.param("customerAddress", "Lungujja")
				.param("customerStatus", "active")
				.param("customerJoinDate", "12/12/2018")
				.param("port", "8585"))
				.andExpect(status().is2xxSuccessful());
				//.andExpect(header().string("Location", "/olifycustomer"));
		
		OlifyCustomer expectedCustomer = new OlifyCustomer();   //Sets up expected customer
		expectedCustomer.setCustomerId(1L);
		expectedCustomer.setCustomerName("Moses Masiga");
		expectedCustomer.setCustomerMobile("0704008863");
		expectedCustomer.setCustomerEmail("emacsone@aol.com");
		expectedCustomer.setCustomerAddress("Lungujja");
		expectedCustomer.setCustomerStatus("status");
		expectedCustomer.setCustomerJoinDate(new Date(12/12/2018));
		expectedCustomer.setPort(8585);
		
		mockMvc.perform(get("/olifycustomer"))  //Performs GET request
		.andExpect(status().isOk())
		.andExpect(view().name("olifycustomer"))
		.andExpect(model().attributeExists("customer"))
		.andExpect(model().attribute("customers", hasSize(1)))
		.andExpect(model().attribute("customers", contains(samePropertyValuesAs(expectedCustomer))));
		
	}
	
	@Test
	public void testHomePage() throws Exception {
		OlifyCustomer customer = new OlifyCustomer();
		customer.setCustomerName("Ajuma");
		
		given(mockCustomerController.findByCustomerId(customer.getCustomerId(), null)).willCallRealMethod();
		
		mockMvc.perform(get("/customers").accept(MediaType.parseMediaType("application/json; charset=UTF-8")))
		.andExpect(status().isOk())
		//.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$[0].customerName", is(customer.getCustomerName())))
		.andReturn().getResponse().getContentAsString();
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testGetCustomers() throws Exception {
		OlifyCustomer customers = new OlifyCustomer();
		customers.setCustomerName("Anne Nazziwa");
		
		//Creates a list of Customers (because we will have just one member, it can be a singeltonList)
		List<OlifyCustomer> allCustomers = Collections.singletonList(customers);
		
		given(mockCustomerController.listAllCustomers(mockCustomer, null)).equals((List<OlifyCustomer>) allCustomers);
		
		mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))  //The JSON object has one member
		.andExpect(jsonPath("$[0].customerName", is(customers.getCustomerName())));  //The JSON body has a customer key with the value we set
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mockCustomerController = null;
		mockCustomerRegistrar = null;
		mockCustomer = null;
		wac = null;
		mockMvc = null;
	}
}