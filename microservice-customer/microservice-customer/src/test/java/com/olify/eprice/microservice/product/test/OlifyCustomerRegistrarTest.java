package com.olify.eprice.microservice.product.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;

public class OlifyCustomerRegistrarTest {

	OlifyCustomerRegistrar mockCustomerRegistrar;
	@Mock
	OlifyCustomerRepository mockCustomerRepository;
	@Mock
	private OlifyCustomer mockCustomer;
	
	private static final String CUSTOMERNAME = "Masiga";
	private static final String CUSTOMERMOBILE = "+256704008863";
	private static final String CUSTOMEREMAIL = "emacsone@aol.com";
	private static final String CUSTOMERADDRESS = "Mutungo";
	private static final String CUSTOMERSTATUS = "active";
	private static final Date CUSTOMERJOINDATE = new Date(11/30/2018);
	private static final int PORT = 8585;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void setUp() throws Exception {
		mockCustomerRegistrar = mock(OlifyCustomerRegistrar.class);
	}
	
	@After
	public void tearDown() throws Exception {
		mockCustomerRepository = null;
	}

	@Test
	public void testCreateCustomers() throws Exception {
		OlifyCustomer inputCustomer = new OlifyCustomer(1L, CUSTOMERNAME, CUSTOMERMOBILE, CUSTOMEREMAIL, CUSTOMERADDRESS, CUSTOMERSTATUS, CUSTOMERJOINDATE, PORT);
		OlifyCustomerRepository mockCustomerRepository = mock(OlifyCustomerRepository.class);
		Mockito.doReturn(mockCustomerRepository).when(mockCustomerRegistrar).getOlifyCustomerRepository();
		mockCustomerRegistrar.createCustomer(inputCustomer);
				
		assertThat(mockCustomerRegistrar.createCustomer(mockCustomer));
	}
}