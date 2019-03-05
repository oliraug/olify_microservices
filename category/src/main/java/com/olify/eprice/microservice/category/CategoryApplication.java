package com.olify.eprice.microservice.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.olify.eprice.microservice.category.Repository.CategoryRepository;
import com.olify.eprice.microservice.category.Service.CategoryService;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class CategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.category");
		bean.setPersistenceUnitName("Categorizing Items");
		bean.setJpaVendorAdapter(getJpaVendorAdapter());
		bean.afterPropertiesSet();
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
		dataSource.setDataSourceClassName("com.mysql.cj.jdbc.Driver");
		dataSource.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/standard_accounting");
		dataSource.addDataSourceProperty("user", "root");
		dataSource.addDataSourceProperty("password", "olify");
		dataSource.addDataSourceProperty("cachePrepStmts", true);
		dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("useSSL", false);
		return dataSource;
	}
	
	/*@Bean
	@ConfigurationProperties("application.properties")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}*/
	
	@Bean(name="categoryService")
	public CategoryService categoryService() {
		return new CategoryService();
	}
	
	@Bean(name="categoryRepository")
	public CategoryRepository categoryRepository() {
		return categoryRepository();
	}
}