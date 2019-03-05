/**
 * 
 */
package com.olify.eprice.microservice.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olify
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderClientApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderClientApplication.class, args);

	}

}

@RestController
class ServiceInstanceRestController{
	@Autowired
	DiscoveryClient discoveryClient;
	
	@RequestMapping(value="/service-instances/{order-service}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String orderService){
		return this.discoveryClient.getInstances(orderService);
	}
}