package com.olify.eprice.microservicecustomer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestTemplate;

import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;
import com.zaxxer.hikari.HikariDataSource;


@SpringBootApplication  //Enables component-scanning and auto-configuration
@EnableDiscoveryClient
@EnableJpaAuditing
public class MicroserviceCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCustomerApplication.class, args); //Bootstrapping the application
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.model");
		bean.setPersistenceUnitName("Categorizing Items");
		bean.setJpaVendorAdapter(getJpaVendorAdapter());
		bean.setDataSource(dataSource());
		return bean;
	}
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
		return adapter; 
	}
	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setMaximumPoolSize(100);
		dataSource.setIdleTimeout(60000);
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/olify_customers");
		dataSource.setUsername("root");
		dataSource.setPassword("olify");
		dataSource.setConnectionTestQuery("SELECT 1");
		dataSource.setPoolName("HikariCP");
		dataSource.setIdleTimeout(10000);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("useSSL", false);
        
		return dataSource;
	}

	@Bean 
	@Qualifier("olifyCustomer")
	public OlifyCustomer olifyCustomer() {
		return new OlifyCustomer();
	}
	
	@Bean 
	@Qualifier("olifyCustomerRepository")
	public OlifyCustomerRepository olifyCustomerRepository() {
		return null;
	}
	
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
	
	/*@Bean
	CommandLineRunner init(OlifyCustomerRepository repo) {
		return (evt) -> {
			repo.save(new OlifyCustomer(1L, "Masiga Moses", "0773405024", "emacsone@aol.com", "Mutungo", null, new Date(), 8585));
			repo.save(new OlifyCustomer(2L, "Willings John", "0750405099", "willings12@gmail.com", "nkumba", null, new Date(), 8585));
		};
	}*/
}