package com.olify.eprice.microservice.product.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olify.eprice.microservice.product.InsufficientProductsException;
import com.olify.eprice.microservice.product.Component.ProductServiceImpl;
import com.olify.eprice.microservice.product.Model.Product;

@RestController
@RequestMapping("/olify/products")
public class ProductController { 
	@Autowired
	private ProductServiceImpl productServiceImpl;

	//To save a product
	@PostMapping("/create")
	public Product createProduct(@Valid @RequestBody Product prod) {
		return productServiceImpl.saveProduct(prod);
	}
	
	//Buy product
	@PostMapping("/buy")
	public boolean buyProduct(@Valid Product prod, int orderedQuantity) throws InsufficientProductsException {
		return productServiceImpl.buy(prod, orderedQuantity);
	}
	
	//Get all products
	@GetMapping("/getAll")
	public List<String> getAllProducts() {
		return productServiceImpl.findAllProducts()
				.stream()
				.map(Product::getProductName)
				.collect(Collectors.toList());
	}
	
	/*get product by id*/
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value="id") Long id){
		Product prod = productServiceImpl.getOne(id);
		
		if(prod == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(prod);
	}
	
	/*update product by id*/
	@PutMapping("update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="id") Long id, @Valid Product productDetails){
		Product prod = productServiceImpl.getOne(id);
		
		if(prod == null) {
			return ResponseEntity.notFound().build();
		}
		prod.setProductName(productDetails.getProductName());
		prod.setPrice(productDetails.getPrice());
		prod.setUnits(productDetails.getUnits());
		prod.setQuantityPerUnit(productDetails.getQuantityPerUnit());
		prod.setUnitsInStock(productDetails.getUnitsInStock());
		prod.setUnitsOnOrder(productDetails.getUnitsOnOrder());
		prod.setProductIntention(productDetails.getProductIntention());
		prod.setProductMeasures(productDetails.getProductMeasures());
		prod.setProductStatus(productDetails.getProductStatus());
		prod.setCreatedDate(productDetails.getCreatedDate());
		prod.setEnteredBy(productDetails.getEnteredBy());
		
		Product updateProduct = productServiceImpl.saveOrUpdate(prod);
		return ResponseEntity.ok().body(updateProduct);
	}
	
	/*Delet a product*/
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Product> delete(@PathVariable(value="id") Long id){
		//First find a product by id
		Product prod = productServiceImpl.getOne(id);
		
		//check for null values
		if(prod == null) {
			return ResponseEntity.notFound().build();
		}
		productServiceImpl.delete(prod);
		return ResponseEntity.ok().build();
	}
}