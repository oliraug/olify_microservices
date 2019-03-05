/**
 * 
 */
package com.olify.eprice.microservice.component;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;

/**
 * @author Olify
 *
 */
@Service
@Component
public class OlifyCustomerRegistrar {
	public final static Logger logger = Logger.getLogger(OlifyCustomerRegistrar.class);
	
	
	@Autowired
	OlifyCustomerRepository repository;
	/*
	 * we injected a Sender component to send a notification to the customer by passing the customer's e-mail address to the sender
	 *@Autowired
	protected RestTemplate restTemplate;
	protected String customersServiceUrl;
	
	//Sender sender;

	//constructor
	public OlifyCustomerRegistrar(String customersServiceUrl, OlifyCustomerRepository repository) {
		this.customersServiceUrl = customersServiceUrl.startsWith("http") ? customersServiceUrl : "http://" + customersServiceUrl;
		//this.sender = sender;
		this.repository = repository;
	}

	public OlifyCustomerRegistrar() {
	}*/

	//Method Used To Create The Hibernate's SessionFactory Object
	public static SessionFactory getSessionFactory() {
    // Creating Configuration Instance & Passing Hibernate Configuration File
	   Configuration config = new Configuration();
	   config.configure("hibernate.cfg.xml");
		 
   // Since Hibernate Version 4.x, Service Registry Is Being Used
       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build(); 

   // Creating Hibernate Session Factory Instance
       SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);      
        return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<OlifyCustomer> findAll() {
		Session session = getSessionFactory().openSession();
		List<OlifyCustomer> customerList = session.createQuery("FROM OlifyCustomer").list();
		session.close();
		return customerList;
		/*OlifyCustomer[] customers = restTemplate.getForObject(customersServiceUrl+"/customers", OlifyCustomer[].class);
		return Arrays.asList(customers);*/
	}

	//This Method is used to display the records from the database table
	@SuppressWarnings("unchecked")
	public List<OlifyCustomer> findAllById(Iterable<Long> customerId) {
		Session session = getSessionFactory().openSession();
		/*Criteria criteria = session.createCriteria(OlifyCustomer.class);
		criteria.add(Restrictions.eq("customerId", customerId));*/
		List<OlifyCustomer> customerList = session.createQuery("FROM olify_customer WHERE customerId = :customerId").list();
		//closing the session object
		session.close();
		logger.info("Customer Records avialable in the database are?= " + customerList.size());
		return customerList;
	}

	public OlifyCustomer getOne(Long customerId) {
		
		return null;
	}

	public long count() {
	
		return 0;
	}

//method used to delete a record from the database table
	public void deleteById(Long customerId) {
		Session session = getSessionFactory().openSession();
		//creating transaction object
		Transaction tx = session.beginTransaction();
		Optional<OlifyCustomer> customer = findById(customerId);
		session.delete(customer);
		//transaction is committed to database
		tx.commit();
		session.close();
		logger.info("Record is successfully deleted!=" + customer.toString());
	}

	public boolean existsById(Long customerId) {
		
		return false;
	}

	public Optional<OlifyCustomer> findById(Long customerId) {
		
		return null;
	}

	//method used to update records to a database table
	public OlifyCustomer saveOrUpdate(OlifyCustomer customer) {
		Session session = getSessionFactory().openSession();
		
		//creating transaction object
		Transaction tx = session.beginTransaction();
		OlifyCustomer updateCustomer = session.load(OlifyCustomer.class,customer.getCustomerId());
		updateCustomer.setCustomerName(customer.getCustomerName());
		updateCustomer.setCustomerMobile(customer.getCustomerMobile());
		updateCustomer.setCustomerEmail(customer.getCustomerEmail());
		updateCustomer.setCustomerAddress(customer.getCustomerAddress());
		updateCustomer.setCustomerStatus(customer.getCustomerStatus());
		updateCustomer.setCustomerJoinDate(customer.getCustomerJoinDate());
		updateCustomer.setPort(customer.getPort());
		session.saveOrUpdate(updateCustomer);
		tx.commit();
		session.close();
		logger.info("Customer record successfully updated!=" + customer.toString());
		return customer;
		
	}

	//Method used to create a new customer record in the database table
	
	public OlifyCustomer createCustomer(final OlifyCustomer customer) {
		Session session = getSessionFactory().openSession();
		//Creating Transaction object
		Transaction transaction = session.beginTransaction();
		session.save(customer);
		//sender.send(customer.getCustomerEmail());
		//Commit Transaction to database
		transaction.commit();
		//closing the session object
		session.close();
		logger.info("Successfully created"+ customer.toString());
		
		return repository.save(customer);
	}

	public Optional<Integer> forCustomer(String customerName) {
		return repository.forCustomer(customerName);
	}

	public List<OlifyCustomer> findByCustomerName(String customerName) {
		return repository.findByCustomerName(customerName);
	}

	public boolean isCustomerNameExist(String customername) {
		
		Session session = getSessionFactory().openSession();
		//Creating Transaction object
		Transaction transaction = session.beginTransaction();
		OlifyCustomer customer = getOne(customername);
		session.contains(customer);
		//Commit Transaction to database
		transaction.commit();
		//closing the session object
		session.close();
		return true;
	}

	private OlifyCustomer getOne(String customername) {
		Session session = getSessionFactory().openSession();
		OlifyCustomer customer = (OlifyCustomer) session.createQuery("FROM olify_customer WHERE customer_name = :customer_name");
		
		//closing the session object
		session.close();
		return customer;
	}

	public void save(OlifyCustomer customer) {
		repository.save(customer);
	}

	public OlifyCustomerRepository getOlifyCustomerRepository() {
		return null;
	}

	public void delete(List<OlifyCustomer> customers) {
		
	}


}
