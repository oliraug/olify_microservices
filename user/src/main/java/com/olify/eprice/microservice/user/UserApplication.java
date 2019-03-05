package com.olify.eprice.microservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaServer
@EnableJpaRepositories(basePackages="com.olify.eprice.microservice.repository ")
@SpringBootApplication //Tells spring boot framework that this class is the entry point for spring boot service
@EnableCaching
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}