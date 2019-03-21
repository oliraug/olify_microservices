/**
 * 
 */
package com.olify.eprice.microservice.category.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.category.model.OlifyCategory;

/**
 * @author Olify
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<OlifyCategory, Long>{
	//OlifyCategory saveCategory(OlifyCategory category);
	OlifyCategory findCategoryById(Long categoryId);
	List<OlifyCategory> findAll();
	void delete(OlifyCategory category);
	OlifyCategory findByCategoryName(String categoryName);
}