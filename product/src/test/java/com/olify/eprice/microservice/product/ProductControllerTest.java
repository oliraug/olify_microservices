package com.olify.eprice.microservice.product;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.product.Component.ProductServiceImpl;
import com.olify.eprice.microservice.product.Controller.ProductController;
import com.olify.eprice.microservice.product.Model.Product;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductServiceImpl mockProductRegistrar;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		mockMvc = null;
	}

	@Test
	public void test_getProductShouldReturnProductDetails() throws Exception {
		given(mockProductRegistrar.findAllProducts()).willReturn(Arrays.asList(new Product("coffee", 0, null, 0, 0, 0, null, null, null, null, null)));
		mockMvc.perform(get("olify/products/getAll"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("productname").value("coffee"));
	}

}
