/**
 * 
 */
package com.olify.eprice.microservice.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.olify.eprice.microservice.component.OlifyProductRegistrar;
import com.olify.eprice.microservice.controller.OlifyProductController;
import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microserviceproduct.MicroserviceProductApplication;

/**
 * @author Olify
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MicroserviceProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class OlifyProductControllerTest {

	@InjectMocks
	OlifyProductController mockProductController;
	@Autowired @Mock
	OlifyProductRegistrar mockProductRegistrar;
	private MockMvc mockMvc;
	
	@Mock private OlifyProduct mockProduct;
	@Autowired 
	private WebApplicationContext wac;
	private RestTemplate restTemplate;
	private int serverPort;

	@Before
    public void init() {
        //MockitoAnnotations.initMocks(this);
    }
	
	@Before
	public void setUp() throws Exception {
		/*this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockProductRegistrar = mock(OlifyProductRegistrar.class, Mockito.RETURNS_DEEP_STUBS);
		restTemplate = new RestTemplate();*/
	}

	@After
	public void tearDown() throws Exception {
		mockProductController = null;
		mockProductRegistrar = null;
		mockProduct = null;
		restTemplate = null;
	}

	@Test
	public void testListAllProducts() throws Exception {
		OlifyProduct product = new OlifyProduct();
		product.setId(1L);
		Mockito.when(mockProductRegistrar.getOne(1L)).thenReturn(product);
		
		ResponseEntity<OlifyProduct> op = mockProductController.getProductById(1L, null);
		Mockito.verify(mockProductRegistrar).getOne(1L);
		
		//assertEquals(1l, op.getProduct().longValue());
	}

	@Test
	public void testGetProduct() throws Exception {
		ResponseEntity<String> resultEntity = restTemplate.getForEntity(customerURL() + "/form.html", String.class);
        assertTrue(resultEntity.getStatusCode().is2xxSuccessful());
        assertTrue(resultEntity.getBody().contains("<form>"));
	
	}

	@Test
	public void testCreateProduct() throws Exception {
		
	}

	private String customerURL() {
		return "http://localhost:" + serverPort + "/";
	}

	@Test
	public void testUpdateProduct() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProduct() throws Exception {
		fail("Not yet implemented");
	}

}
