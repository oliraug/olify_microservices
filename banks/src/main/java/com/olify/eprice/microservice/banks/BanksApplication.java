package com.olify.eprice.microservice.banks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.olify.eprice.microservice.banks.component.BankRepositoryImpl;
import com.olify.eprice.microservice.banks.controller.BankController;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class BanksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanksApplication.class, args);
	}
	
	@Bean
	public BankController bankController() {
		return new BankController();
	}

	@Bean
	public BankRepositoryImpl bankRepositoryImpl() {
		return new BankRepositoryImpl();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.banks");
		bean.setPersistenceUnitName("Banks");
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
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/olify_markets");
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

