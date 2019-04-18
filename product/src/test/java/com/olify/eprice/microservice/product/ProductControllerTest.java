package com.olify.eprice.microservice.product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.product.Component.ProductRegistrar;
import com.olify.eprice.microservice.product.Controller.ProductController;
import com.olify.eprice.microservice.product.Model.Product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductRegistrar mockProductRegistrar;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		mockMvc = null;
	}

	@Test
	public void test_getProductShouldReturnProduct() throws Exception {
		given(mockProductRegistrar.getProductDetails(containsString("coffee"))).willReturn(new Product("coffee", 0, null, 0, 0, 0, null, null, null, null, null));
		mockMvc.perform(get("/products/coffee"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("productname").value("coffee"));
	}

}
