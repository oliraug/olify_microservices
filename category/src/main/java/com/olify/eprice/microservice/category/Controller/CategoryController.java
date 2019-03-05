package com.olify.eprice.microservice.category.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olify.eprice.microservice.category.Service.CategoryService;
import com.olify.eprice.microservice.category.model.OlifyCategory;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "CATEGORIES", description = "Item Categories")  //Swagger Annotation
@RestController
@ExposesResourceFor(OlifyCategory.class)
@RequestMapping(value="/olify", produces="application/json")
public class CategoryController {

	@Autowired private CategoryService categoryService;

	/*Get category by id*/
	@GetMapping(value="/categories/{id}")
	@ApiOperation(value = "Get a category", notes = "Return category")
	public OlifyCategory getCategoryById(@PathVariable Long id) {
		return categoryService.findOne(id);
	}

}
