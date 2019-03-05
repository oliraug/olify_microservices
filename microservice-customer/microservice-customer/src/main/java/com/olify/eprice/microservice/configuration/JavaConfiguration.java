/**
 * class that contain spring beans configurations
 */
package com.olify.eprice.microservice.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.olify.eprice.microservice.component.OlifyCustomerFormatter;
import com.olify.eprice.microservice.controller.OlifyCustomerController;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;

/**
 * @author Olify
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.olify.eprice.microservice.*"}, excludeFilters = {@Filter(type = FilterType.ANNOTATION, value=Configuration.class)})
@EnableTransactionManagement
//@Import({SecurityConfig.class, PersistenceConfig.class, WebMvcConfig.class, SpringWebAppInitializer.class})
//@PropertySource(value={"classpath:hibernate.properties"})
//@ImportResource(value={"classpath:application.properties"})
@EnableScheduling
@EnableCaching
@EnableJpaRepositories(basePackages="com.olify.eprice.microservice.repository.*")
public class JavaConfiguration extends WebMvcConfigurationSupport{
	
	@Bean 
	@Qualifier("olifyCustomerRepository")
	public OlifyCustomerRepository olifyCustomerRepository() {
		return null;
	}
	
	@Bean
	@Qualifier("olifyCustomerFormatter")
	public OlifyCustomerFormatter olifyCustomerFormatter() {
		return new OlifyCustomerFormatter(null);
	}
	//@Bean allows us to create a bean in a spring configuration class
	@Bean 
	
	/* @Scope allows us to force olifyCustomerController() to be executed each time is called
	 *  and return a different object each time
	 */
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) 
	@Qualifier("olifyCustomerController")
	public OlifyCustomerController olifyCustomerController() {
		OlifyCustomerController olifyCustomerController = new OlifyCustomerController();
		Properties olifyCustomerRegistrar = new Properties();
		olifyCustomerRegistrar.put(olifyCustomerController, olifyCustomerRegistrar);
		return new OlifyCustomerController();
	}

	@Bean 
	@Qualifier("restTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	@Bean 
	@Qualifier("olifyCustomer")
	public OlifyCustomer olifyCustomer() {
		return new OlifyCustomer();
	}
	
	@Bean
	@Qualifier("webApplicationContext")
	public WebApplicationContext webApplicationContext() {
		return null;
	}
	@Bean
	@Qualifier("mockMvc")
	public MockMvc mockMvc() {
		return null;
	}
	
	/*@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.exposeIdsFor(OlifyCustomer.class);
			}
		};
	}
	**/
	
	@Bean
	@Qualifier("entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		return null;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}