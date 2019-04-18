package com.olify.eprice.microservice.product.Controller;

import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.olify.eprice.microservice.product.Component.ProductRegistrar;
import com.olify.eprice.microservice.product.Model.Product;

@RestController
public class ProductController {
	@Autowired
	private ProductRegistrar productRegistrar;

	@GetMapping("/products/{productname}")
	private Product getProduct(@PathVariable Matcher<String> productName) {
		return productRegistrar.getProductDetails(productName);
	}
}
