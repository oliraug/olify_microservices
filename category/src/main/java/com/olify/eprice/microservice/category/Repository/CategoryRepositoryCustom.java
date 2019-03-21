package com.olify.eprice.microservice.category.Repository;

import java.util.List;

import com.olify.eprice.microservice.category.model.OlifyCategory;

public interface CategoryRepositoryCustom {

	OlifyCategory saveCategory(OlifyCategory category);

	List<OlifyCategory> findAll();

	void deleteCategory(OlifyCategory category);

	OlifyCategory findByCategoryName(String categoryName);

	OlifyCategory findOne(Long id);
}