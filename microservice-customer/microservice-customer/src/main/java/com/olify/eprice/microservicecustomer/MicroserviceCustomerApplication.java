package com.olify.eprice.microservicecustomer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;

@EnableEurekaServer
@SpringBootApplication  //Enables component-scanning and auto-configuration

public class MicroserviceCustomerApplication {
	@Bean 
	@Qualifier("olifyCustomerRepository")
	public OlifyCustomerRepository olifyCustomerRepository() {
		return null;
	}
	
	/*@Bean
	@Qualifier("olifyCustomerFormatter")
	public OlifyCustomerFormatter olifyCustomerFormatter() {
		return new OlifyCustomerFormatter(null);
	}*/
	
	@Bean
	@Qualifier("olifyCustomerRegistrar")
	public OlifyCustomerRegistrar olifyCustomerRegistrar() {
		return new OlifyCustomerRegistrar();
	}
	
	@Bean 
	@Qualifier("restTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ApplicationContext context =
		SpringApplication.run(MicroserviceCustomerApplication.class, args); //Bootstrapping the application
		for(String name:context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
	
	@Bean
	CommandLineRunner init(OlifyCustomerRepository repo) {
		return (evt) -> {
			repo.save(new OlifyCustomer(1L, "Masiga Moses", "0773405024", "emacsone@aol.com", "Mutungo", null, new Date(), 8585));
			repo.save(new OlifyCustomer(2L, "Willings John", "0750405099", "willings12@gmail.com", "nkumba", null, new Date(), 8585));
		};
	}
}