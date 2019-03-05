/**
 * 
 */
package com.olify.eprice.microservice.category.Service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.category.Repository.CategoryRepository;
import com.olify.eprice.microservice.category.model.OlifyCategory;

/**
 * @author Olify
 *
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	
	//Save category
	public OlifyCategory saveCategory(OlifyCategory category) {
		return categoryRepository.save(category);
	}
	
	//Get all categories	
	public List<OlifyCategory> findAll(){
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<OlifyCategory> criteria = builder.createQuery(OlifyCategory.class);
		Root<OlifyCategory> contactRoot = criteria.from(OlifyCategory.class);
		criteria.select(contactRoot);		
		return session.createQuery(criteria).getResultList();
	}
	
	//Update category
	
	//Delete category
	public void deleteCategory(OlifyCategory category) {
		categoryRepository.delete(category);
	}
	
	//Get category by id
	public OlifyCategory findOne(Long id) {
		return categoryRepository.getOne(id);
	}
}
