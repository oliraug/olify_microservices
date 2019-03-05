package com.olify.eprice.microservice.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.category.Controller.CategoryController;
import com.olify.eprice.microservice.category.Service.CategoryService;
import com.olify.eprice.microservice.category.model.OlifyCategory;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryControllerTest {
	@InjectMocks
	CategoryController categoryController;
	@Mock //this fixes our NullPointerException when testing the controller
	CategoryService categoryService;
	OlifyCategory category;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		categoryController = null;
		categoryService = null;
		category = null;
	}
/*
 * Method to get market by id
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
}