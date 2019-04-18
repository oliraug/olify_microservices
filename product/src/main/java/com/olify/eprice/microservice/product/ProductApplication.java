package com.olify.eprice.microservice.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.olify.eprice.microservice.product.Component.ProductRegistrar;
import com.olify.eprice.microservice.product.Controller.ProductController;
import com.olify.eprice.microservice.product.Model.Product;
import com.olify.eprice.microservice.product.Repository.ProductRepository;
import com.olify.eprice.microservice.product.Repository.ProductService;
import com.zaxxer.hikari.HikariDataSource;


@EnableDiscoveryClient
//@EnableZuulProxy
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
@EnableJpaAuditing
@EnableJpaRepositories(basePackages="com.olify.eprice.microservice.product.Repository")
@EnableTransactionManagement
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean(name="productRepository")
	public ProductRepository productRepository() {
		return null;		
	}
	
	@Bean(name="productService")
	public ProductService productService() {
		return null;		
	}
	
	@Bean(name="productRegistrar")
	public ProductRegistrar productRegistrar() {
		return new ProductRegistrar();		
	}
	
	@Bean(name="product")
	public Product product() {
		return new Product();		
	}
	
	@Bean(name="productController")
	public ProductController productController() {
		return new ProductController();		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.product.Model");
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
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/products");
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