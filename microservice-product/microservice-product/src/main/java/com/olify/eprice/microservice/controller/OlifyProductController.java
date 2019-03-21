/**
 * 
 */
package com.olify.eprice.microservice.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.olify.eprice.microservice.component.OlifyProductRegistrar;
import com.olify.eprice.microservice.model.OlifyProduct;

/**
 * @author olify
 *
 */
@RestController
@RequestMapping(value="/olify")
public class OlifyProductController {
	@Autowired private OlifyProductRegistrar productRegistrar;
	/*logger for this class and subclasses*/
	protected final Log logger = LogFactory.getLog(getClass());
	
	/*Retrieve all products*/
	@GetMapping(value="/products", produces="application/json")
	public ResponseEntity<List<OlifyProduct>> listAllProducts(HttpServletRequest request){
		List<OlifyProduct> products = productRegistrar.findAllProducts();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8"); 
		/*To generate a Device object from HttpServletRequest*/
		Device currentDevice = DeviceUtils.getCurrentDevice(request);
		if(currentDevice.isNormal()) {
			logger.info("Desktop user");
			}
		else if(currentDevice.isMobile()) {
			logger.info("Mobile user");
			}
		else if(currentDevice.isTablet()) {
			logger.info("Tablet user");
		}
			if(products.isEmpty()){
				return new ResponseEntity<List<OlifyProduct>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<OlifyProduct>>(products, headers, HttpStatus.OK);
	}
	
	/*
	 * Get a single product
	 */
	@GetMapping(value="olifyproduct/{id}", produces="application/json")
	public ResponseEntity<OlifyProduct> getProductById(@PathVariable(value="productId") Long id, HttpServletRequest request){
		OlifyProduct product = productRegistrar.getOne(id);
		/*To generate a Device object from HttpServletRequest*/
		Device currentDevice = DeviceUtils.getCurrentDevice(request);
		if(currentDevice.isNormal()) {
			logger.info("Desktop user");
			}
		else if(currentDevice.isMobile()) {
			logger.info("Mobile user");
			}
		else if(currentDevice.isTablet()) {
			logger.info("Tablet user");
		}
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(product);
	}
	
	
	/*create a product*/
	@PostMapping(value="/olifyproduct", produces="application/json")
	public ResponseEntity<OlifyProduct> createProduct(@PathVariable String productname, @RequestBody OlifyProduct product, UriComponentsBuilder ucBuilder, HttpServletRequest request){
		System.out.println("Creating product" + product.getProductName());
		if(productRegistrar.isProductNameExist(productname)){
			System.out.println("A product with name" + product.getProductName() +"already exists");
			return new ResponseEntity<OlifyProduct>(HttpStatus.CONFLICT);
		} 
		/*To generate a Device object from HttpServletRequest*/
		Device currentDevice = DeviceUtils.getCurrentDevice(request);
		if(currentDevice.isNormal()) {
			logger.info("Desktop user");
			}
		else if(currentDevice.isMobile()) {
			logger.info("Mobile user");
			}
		else if(currentDevice.isTablet()) {
			logger.info("Tablet user");
		}
		productRegistrar.createProduct(product);
		productRegistrar.saveProduct(product);
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/olifyproduct/{productId}").build().expand(product.getId()).toUri();
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Product created-", String.valueOf(product.getProductName()));
		headers.add("Content-Type","application/json; charset=utf-8");
		headers.setLocation(location);
		final ResponseEntity<OlifyProduct> entity = new ResponseEntity<OlifyProduct>(headers,HttpStatus.CREATED);
		return entity;
	}
	
	/*create a update product*/
	@PutMapping(value="/olifyproduct/{productId}")
	public ResponseEntity<OlifyProduct> updateProduct(@PathVariable ("productId") Long productId, @RequestBody OlifyProduct product, HttpServletRequest request){
		/*To generate a Device object from HttpServletRequest*/
		Device currentDevice = DeviceUtils.getCurrentDevice(request);
		if(currentDevice.isNormal()) {
			logger.info("Desktop user");
			}
		else if(currentDevice.isMobile()) {
			logger.info("Mobile user");
			}
		else if(currentDevice.isTablet()) {
			logger.info("Tablet user");}
		HttpHeaders headers = new HttpHeaders();
		OlifyProduct isExist = productRegistrar.getOne(productId);
		if(isExist == null){
			return new ResponseEntity<OlifyProduct>(HttpStatus.NOT_FOUND);
		}else if(product == null){
			return new ResponseEntity<OlifyProduct>(HttpStatus.BAD_REQUEST);
		}
		isExist.setProductName(product.getProductName());
		isExist.setPrice(product.getPrice());
		isExist.setUnits(product.getUnits());
		isExist.setQuantityPerUnit(product.getQuantityPerUnit());
		isExist.setUnitsInStock(product.getUnitsInStock());
		isExist.setUnitsOnOrder(product.getUnitsOnOrder());
		isExist.setProductMeasures(product.getProductMeasures());
		isExist.setQuantityPerUnit(product.getQuantityPerUnit());
		isExist.setProductIntention(product.getProductIntention());
		isExist.setProductStatus(product.getProductStatus());
		isExist.setCreatedDate(product.getCreatedDate());
		isExist.setEnteredBy(product.getEnteredBy());
		
		productRegistrar.saveOrUpdate(product);
		
		headers.add("Product updated", String.valueOf(product.getId()));
		return new ResponseEntity<OlifyProduct>(product, headers, HttpStatus.OK);
		}
	
	/*
	 * Delete a product
	 */
	@DeleteMapping(value="/olifyproduct/{productid}")
	public ResponseEntity<OlifyProduct> deleteProduct(@PathVariable String productname, @PathVariable Long productId,
			@RequestBody OlifyProduct product, HttpServletRequest request){
		/*To generate a Device object from HttpServletRequest*/
		Device currentDevice = DeviceUtils.getCurrentDevice(request);
		if(currentDevice.isNormal()) {
			logger.info("Desktop user");
			}
		else if(currentDevice.isMobile()) {
			logger.info("Mobile user");
			}
		else if(currentDevice.isTablet()) {
			logger.info("Tablet user");
			}
		if(!productRegistrar.isProductNameExist(productname)) {
			return new ResponseEntity<OlifyProduct>(HttpStatus.NOT_FOUND);
		}
		productRegistrar.deleteProduct(productId);
		productRegistrar.saveProduct(product);
		return new ResponseEntity<OlifyProduct>(HttpStatus.OK); 
	}
}