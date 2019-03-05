/**
 * 
 */
package com.olify.eprice.microservice.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Olify
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.olify.eprice.microservice.*"}, excludeFilters = {@Filter(type = FilterType.ANNOTATION, value=Configuration.class)})
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
public class JavaConfiguration extends WebMvcConfigurationSupport implements TransactionManagementConfigurer{

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return null;
	}
}