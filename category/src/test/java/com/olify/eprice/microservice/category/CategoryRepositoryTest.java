package com.olify.eprice.microservice.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.category.CategoryApplication;
import com.olify.eprice.microservice.category.Repository.CategoryRepository;
import com.olify.eprice.microservice.category.model.OlifyCategory;
import com.olify.eprice.microservice.category.model.OlifyUser;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=CategoryApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CategoryRepositoryTest {
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private TestEntityManager entityManager;
	
	private String categoryName = "Fruits";
	private OlifyUser user = new OlifyUser("olify@olify.com", "password", true, "image.png", true, true);
	private String categoryStatus = "Active";
	private String description = "Fruits are delicious";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		entityManager.clear();
		categoryRepository.flush();
	}

	@Test
	public void test_getCategoryReturnsCategoryDetails() throws Exception {
		OlifyCategory savedCategory = entityManager.persistFlushFind(new OlifyCategory(user, categoryName, categoryStatus, description));
		OlifyCategory category = categoryRepository.findByCategoryName(categoryName);
		
		assertThat(category.getCategoryName()).isEqualTo(savedCategory.getCategoryName());
	}

	@Test
	public void test_whenCategoryIsSavedThenFindByCategoryName() throws Exception {
		categoryRepository.save(new OlifyCategory(user, categoryName, categoryStatus, description));
		
		assertThat(categoryRepository.findByCategoryName(categoryName)).isNotNull();
	}
	
	@Test
	public void test_shouldFindNoCategoriesIfRepositoryIsEmpty() throws Exception {
		Iterable<OlifyCategory> categories = categoryRepository.findAll();
		
		assertThat(categories).isEmpty();
	}
	
	@Test
	public void test_shouldDeleteAllCategories() throws Exception {
		entityManager.persist(new OlifyCategory(user, "Fish", "Active", "Deal in buying and selling of all sorts of fish and its products"));
		entityManager.persist(new OlifyCategory(user, "Cereals", "Active", "Deal in buying and selling of all sorts of cereal crops"));
		
		categoryRepository.deleteAll();
		
		assertThat(categoryRepository.findAll()).isEmpty();		
	}
	
	@Test
	public void test_shouldFindAllCategories() throws Exception {
		OlifyCategory category = new OlifyCategory(user, "Vegetables", "Active", "Deal in cultivating and selling of all sorts of vegetables");
		entityManager.persist(category);
		OlifyCategory categoryOne = new OlifyCategory(user, "Poultry", "Active", "Deal in rearing and selling of all sorts of poultry");
		entityManager.persist(categoryOne);
		OlifyCategory categoryTwo = new OlifyCategory(user, "Fertilizers", "Active", "Deal in selling of all sorts of fertilizers");
		entityManager.persist(categoryTwo);
		
		Iterable<OlifyCategory> categories = categoryRepository.findAll();
		
		assertThat(categories).hasSize(3).contains(category, categoryOne, categoryTwo);
		
	}
}