package com.olify.eprice.microservice.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.category.Controller.CategoryController;
import com.olify.eprice.microservice.category.Service.CategoryService;
import com.olify.eprice.microservice.category.model.OlifyCategory;

//@RunWith(MockitoJUnitRunner.Silent.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CategoryApplication.class, webEnvironment =SpringBootTest.WebEnvironment.MOCK )
@AutoConfigureMockMvc
public class CategoryControllerTest {
	@InjectMocks
	CategoryController categoryController;
	@Mock //this fixes our NullPointerException when testing the controller
	CategoryService categoryService;
	OlifyCategory category;
	@Autowired private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		categoryController = null;
		categoryService = null;
		category = null;
	}
/*
 * Method to get category by id
 */
	@Test
	public void test_shouldGetCategoryById() throws Exception {
		category = new OlifyCategory();
		category.setId(1l);
		when(categoryService.findOne(1l)).thenReturn(category);
		OlifyCategory cat = categoryController.getCategoryById(1L);
		verify(categoryService).findOne(1l);
		assertThat(cat.getId().longValue()).isEqualTo(1L);
	}
	
	@Test
	public void test_getCategory_ShouldReturnCategory() throws Exception {
		String json = "{\"id\":\"1l\",\"categoryname\":\"Cereals\",\"value\":\"Cereals\"}";
		//given(mockBankRepositoryImpl.getBankDetails(anyString())).willReturn(new Bank("Equity"));
		mockMvc.perform(get("/olify/categories", 1l)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());
	}
}