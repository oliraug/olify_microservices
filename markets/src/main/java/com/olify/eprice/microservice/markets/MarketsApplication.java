package com.olify.eprice.microservice.markets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.olify.eprice.microservice.markets.Component.MarketsRepositoryImpl;
import com.olify.eprice.microservice.markets.Repository.MarketsRepository;
import com.olify.eprice.microservice.markets.Repository.MarketsRepositoryCustom;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class MarketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketsApplication.class, args);
	}
	
	@Bean(name="marketsRepository")
	public MarketsRepository marketsRepository() {
		return null;
	}
	
	@Bean(name="marketsRepositoryImpl")
	public MarketsRepositoryImpl marketsRepositoryImpl() {
		return new MarketsRepositoryImpl();
	}
	
	@Bean(name="marketsRepositoryCustom")
	public MarketsRepositoryCustom marketsRepositoryCustom() {
		return null;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.markets");
		bean.setPersistenceUnitName("Markets");
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