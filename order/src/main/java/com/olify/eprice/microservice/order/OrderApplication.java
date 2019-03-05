package com.olify.eprice.microservice.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

//@EnableGlobalMethodSecurity
@SpringBootApplication
@EnableEurekaServer
public class OrderApplication extends SpringBootServletInitializer{
	@Bean
	public SimpleMeterRegistry simpleMeterRegistry() {
		return new SimpleMeterRegistry();		
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OrderApplication.class);
    }
	@Bean 
    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
      return new HibernateExceptionTranslator(); 
    }
	
	@Bean
	public EurekaClientConfigBean eurekaClientConfigBean() {
		return new EurekaClientConfigBean();
	}
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}