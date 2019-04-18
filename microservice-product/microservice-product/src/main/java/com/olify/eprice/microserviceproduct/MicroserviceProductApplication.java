package com.olify.eprice.microserviceproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.olify.eprice.microservice.component.OlifyProductRegistrar;
import com.olify.eprice.microservice.controller.OlifyProductController;
import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.repository.OlifyProductRepository;
import com.zaxxer.hikari.HikariDataSource;

@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableZuulProxy
@EnableJpaAuditing
@SpringBootApplication
public class MicroserviceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductApplication.class, args);
	}

	@Bean(name="olifyProductRepository")
	public OlifyProductRepository olifyProductRepository() {
		return null;		
	}
	
	
	@Bean(name="olifyProductRegistrar")
	public OlifyProductRegistrar olifyProductRegistrar() {
		return new OlifyProductRegistrar();
	}
	
	@Bean(name="olifyProduct")
	public OlifyProduct olifyProduct() {
		return new OlifyProduct();
	}
	
	@Bean(name="olifyProductController")
	public OlifyProductController olifyProductController() {
		return new OlifyProductController();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.model");
		bean.setPersistenceUnitName("Products");
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
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/olify_products");
		dataSource.setUsername("root");
		dataSource.setPassword("olify");
		dataSource.addDataSourceProperty("cachePrepStmts", true);
		dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("useSSL", false);
		return dataSource;
	}
}