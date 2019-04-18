/**
 * 
 */
package com.olify.eprice.microservice.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.model.OlifyCustomer;

/**
 * @author Olify
 *
 */
@RestController
@RequestMapping(value="/olify")
public class OlifyCustomerController {
	//OlifyCustomerRegistrar component is added to handle the business logic
		@Autowired OlifyCustomerRegistrar customerRegistrar;		
		
		/*logger for this class and subclasses*/
		protected final Log logger = LogFactory.getLog(getClass());
		
		public OlifyCustomerController(OlifyCustomerRegistrar customerRegistrar) {
			this.customerRegistrar = customerRegistrar;
		}
		
		/*Retrieve all customers*/
		@GetMapping(value="/customers", produces="application/json")
		public ResponseEntity<List<OlifyCustomer>> listAllCustomers(@Valid @RequestBody OlifyCustomer customer, HttpServletRequest request){
			List<OlifyCustomer> customers = customerRegistrar.findAll();
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
				if(customers.isEmpty()){
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.ok().build();
		}
		
		/*
		 * Get a single customer
		 */
		@GetMapping(value="olifycustomer/{customerId}", produces="application/json")
		public ResponseEntity<OlifyCustomer> getCustomer(@PathVariable Long customerId, HttpServletRequest request){
			customerRegistrar.getOne(customerId);
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
			return ResponseEntity.ok().build();
		}
		
		/*
		 * Fetch customers with given id
		 */
		@GetMapping(value="/olifycustomer/{customer_id}", produces="application/json")
		public ResponseEntity<OlifyCustomer> findByCustomerId(@PathVariable(value="customerId") Long customerId, HttpServletRequest request){
			logger.info(String.format("customer-service findByCustomerId() invoked:{} for {}", customerRegistrar.getClass().getName(), customerId));
			OlifyCustomer customer = customerRegistrar.getOne(customerId);
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
			
				return ResponseEntity.ok().body(customer);
		}
			
		
		/*create a customer*/
		@PostMapping(value="/olifycustomer", produces="application/json")
		public ResponseEntity<OlifyCustomer> createCustomer(@PathVariable String customername, @RequestBody OlifyCustomer customer, HttpServletRequest request){
			System.out.println("Creating customer" + customer.getCustomerName());
			if(customerRegistrar.isCustomerNameExist(customername)){
				System.out.println("A customer with name" + customer.getCustomerName() +"already exists");
				return new ResponseEntity<OlifyCustomer>(HttpStatus.CONFLICT);
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
			customerRegistrar.createCustomer(customer);
			customerRegistrar.save(customer);
			final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/olifycustomer/{customerId}").build().expand(customer.getCustomerId()).toUri();
			final HttpHeaders headers = new HttpHeaders();
			headers.add("Customer created-", String.valueOf(customer.getCustomerName()));
			headers.add("Content-Type","application/json; charset=utf-8");
			headers.setLocation(location);
			final ResponseEntity<OlifyCustomer> entity = new ResponseEntity<OlifyCustomer>(headers,HttpStatus.CREATED);
			return entity;
		}
		
		/*create a update product*/
		@PutMapping(value="/olifycustomer/{customerId}")
		public ResponseEntity<OlifyCustomer> updateCustomer(@PathVariable ("customerId") Long customerId, @RequestBody OlifyCustomer customer, HttpServletRequest request){
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
			OlifyCustomer isExist = customerRegistrar.getOne(customerId);
			if(isExist == null){
				return ResponseEntity.notFound().build();
			}
			  
			isExist.setCustomerName(customer.getCustomerName());
			isExist.setCustomerMobile(customer.getCustomerMobile());
			isExist.setCustomerEmail(customer.getCustomerEmail());
			isExist.setCustomerAddress(customer.getCustomerAddress());
			isExist.setCustomerJoinDate(customer.getCustomerJoinDate());
			isExist.setCustomerStatus(customer.getCustomerStatus());
			OlifyCustomer updateCustomer = customerRegistrar.saveOrUpdate(isExist);
			
			headers.add("Customer updated", String.valueOf(customer.getCustomerId()));
			return ResponseEntity.ok().body(updateCustomer);
			}
		
		/*
		 * Delete a product
		 */
		@DeleteMapping(value="/olifycustomer/{customerId}")
		public ResponseEntity<OlifyCustomer> deleteCustomer(@PathVariable(value="customerId") Long customerId, HttpServletRequest request){
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
			
			OlifyCustomer isExist = customerRegistrar.getOne(customerId);
			if(isExist == null){
				return ResponseEntity.notFound().build();
			}
			customerRegistrar.deleteById(customerId);
			return ResponseEntity.ok().build(); 
		}
		
		
		/*private List<String> getCustomersByName(@PathVariable("customername") String customername) {
			return customerRegistrar.findByCustomerName(customername)
					.stream()
					.map(OlifyCustomer::getCustomerName)
					.collect(Collectors.toList());
			}*/
}