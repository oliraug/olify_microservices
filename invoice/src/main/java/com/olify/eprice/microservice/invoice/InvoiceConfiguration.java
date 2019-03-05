/**
 * Configuring the application context
 */
package com.olify.eprice.microservice.invoice;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.olify.eprice.microservice.invoice.component.InvoiceRegistrar;
import com.olify.eprice.microservice.invoice.model.OlifyInvoice;
import com.olify.eprice.microservice.invoice.repository.InvoiceRepository;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Olify
 *
 */
@ConfigurationProperties("application.properties")
@Configuration
@EnableKafka //@EnableKafka is required to enable detection of @KafkaListener annotation on spring managed beans
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.olify.eprice.microservice.invoice.repository.InvoiceRepository")
public class InvoiceConfiguration {
	private Environment env;
	private String groupId = "101";

	@Bean 
	public ServletWebServerFactory servletWebServerFactory(){
	  return new TomcatServletWebServerFactory();
	  }
	@Bean
	public InvoiceRepository invoiceRepository() {
		return null;		
	}
	
	@Bean
	public InvoiceRegistrar invoiceRegistrar() {
		return new InvoiceRegistrar();		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.invoice.model");
		bean.setPersistenceUnitName("invoicing");
		bean.setJpaVendorAdapter(getJpaVendorAdapter());
		//bean.setDataSource(getDataSource());
		return bean;
	}

	@Bean
	public HibernateJpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
		return adapter;
	}
	
	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		//dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/stock?autoReconnect=true");
		dataSource.setUsername("root");
		dataSource.setPassword("olify");
		return dataSource;
	}
	
	/*@Bean(name="dataSource")
	public BasicDataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		//dataSource.setDriverClassName(env.getProperty("com.mysql.cj.jdbc.Driver"));
		dataSource.setUrl(env.getProperty("jdbc:mysql://localhost:3306/stock?autoReconnect=true"));
		dataSource.setUsername(env.getProperty("root"));
		dataSource.setPassword(env.getProperty("olify"));
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory(EntityManagerFactory emf){
		if(emf.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Factory is not hibernate factory");
		}
		return emf.unwrap(SessionFactory.class);
	}*/
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
		config.setProperties(hibernateProperties());
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.olify.eprice.microservice.invoice.model");
		return builder.buildSessionFactory(registry);
	}

	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("org.hibernate.dialect.MySQL5Dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("update"));
		properties.put("hibernate.show_sql", env.getProperty("true"));
		properties.put("hibernate.format_sql", env.getProperty("true"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory factory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(factory);
		return transactionManager;
	}
	
	/*
	 *  KafkaTemplate wraps a Producer instance and provides convenience methods for sending messages to Kafka topics
	 */
	@Bean
	public KafkaTemplate<String, OlifyInvoice> kafkaTemplate(){		
		return new KafkaTemplate<String, OlifyInvoice>(kafkaProducerFactory());
	}

	/*
	 * configure a ProducerFactory which sets the strategy for creating Kafka Producer instances
	 */
	@Bean
	public ProducerFactory<String, OlifyInvoice> kafkaProducerFactory() {
		Map<String, Object> config = new HashMap<>();
	    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8585");
	    config.put(ProducerConfig.RETRIES_CONFIG, 6000);
	    config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
	    config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	    config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
	    config.put(ProducerConfig.CLIENT_ID_CONFIG, "001");
	    config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "11002242");
	    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    return new DefaultKafkaProducerFactory<>(config);
	}
	
	/*
	 * For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory
	 */
	@Bean
    public ConsumerFactory<String, OlifyInvoice> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8585");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, OlifyInvoice> kafkaListenerContainerFactory() {    
        ConcurrentKafkaListenerContainerFactory<String, OlifyInvoice> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
	
	@Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8585");
        return new KafkaAdmin(configs);
    }
}