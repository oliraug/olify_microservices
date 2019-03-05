/**
 * 
 */
package com.olify.eprice.microservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.product.InsufficientProductsException;
import com.olify.eprice.microservice.repository.OlifyProductRepository;

/**
 * @author olify
 *
 */
@Component
public class OlifyProductRegistrar {
	@Autowired
	OlifyProductRepository productRepo;
	
	public final static Logger logger = Logger.getLogger(OlifyProductRegistrar.class);
	
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

		public List<OlifyProduct> findAllProducts() {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<OlifyProduct> productsList = session.createQuery("FROM olify_product").list();
			session.close();
			return productsList;
		}
		
		public void deleteProduct(Long productId) {
			Session session = getSessionFactory().openSession();
			//creating transaction object
			Transaction tx = session.beginTransaction();
			Optional<OlifyProduct> product = findById(productId);
			session.delete(product);
			//transaction is committed to database
			tx.commit();
			session.close();
			logger.info("Record is successfully deleted!=" + product.toString());
		
		}

		@SuppressWarnings("unchecked")
		private Optional<OlifyProduct> findById(Long productId) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(OlifyProduct.class);
			criteria.add(Restrictions.eq("productId", productId));
			Optional<OlifyProduct> product = (Optional<OlifyProduct>) criteria.uniqueResult();
			session.close();
			return product;
		}
		
		//method used to update records to a database table		
		public void saveOrUpdate(OlifyProduct product) {
				Session session = getSessionFactory().openSession();
				
				//creating transaction object
				Transaction tx = session.beginTransaction();
				OlifyProduct updateProduct = session.load(OlifyProduct.class,product.getProductId());
				updateProduct.setProductName(product.getProductName());
				updateProduct.setUnits(product.getUnits());
				updateProduct.setPrice(product.getPrice());
				updateProduct.setUnitsInStock(product.getUnitsInStock());
				updateProduct.setUnitsOnOrder(product.getUnitsOnOrder());
				updateProduct.setQuantityPerUnit(product.getQuantityPerUnit());
				updateProduct.setProductMeasures(product.getProductMeasures());
				updateProduct.setProductIntention(product.getProductIntention());
				updateProduct.setCreatedDate(product.getCreatedDate());
				updateProduct.setEnteredBy(product.getEnteredBy());
				session.update(updateProduct);
				tx.commit();
				session.close();
				logger.info("Product record successfully updated!=" + product.toString());
			}
		
		public boolean isProductNameExist(String productname) {
			Session session = getSessionFactory().openSession();
			//Creating Transaction object
			Transaction transaction = session.beginTransaction();
			OlifyProduct product = getOne(productname);
			session.contains(product);
			//Commit Transaction to database
			transaction.commit();
			//closing the session object
			session.close();
			return true;
		}

		//getting product by name
		public OlifyProduct getOne(String productname) {
			Session session = getSessionFactory().openSession();
			OlifyProduct product = (OlifyProduct) session.createQuery("FROM olify_product WHERE product_name = :product_name");
			
			//closing the session object
			session.close();
			return product;
		}

		public Long createProduct(OlifyProduct product) {
			Session session = getSessionFactory().openSession();
			//Creating Transaction object
			Transaction transaction = session.beginTransaction();
			session.save(product);
			//Commit Transaction to database
			transaction.commit();
			//closing the session object
			session.close();
			logger.info("Successfully created"+ product.toString());
			
			return product.getProductId();
			
		}

		//Retrieving product by id
		public OlifyProduct getProductById(Long productId) {
			Session session = getSessionFactory().openSession();
			OlifyProduct product = (OlifyProduct) session.createQuery("FROM olify_product WHERE product_id = :product_id");
			
			//closing the session object
			session.close();
			return product;
		}

		public OlifyProduct saveProduct(OlifyProduct product) {
			Session session = getSessionFactory().openSession();
			//Creating Transaction object
			Transaction transaction = session.beginTransaction();
			session.save(product);
			//Commit Transaction to database
			transaction.commit();
			//closing the session object
			session.close();
			logger.info("Successfully created"+ product.toString());
			return product;
		}

		public boolean buy(OlifyProduct product, int orderedQuantity) throws InsufficientProductsException {
			boolean transactionStatus = false;
			//called getAvailableProducts() of OlifyProductRepository to check if sufficient quantity of the specified product is available
			int availableQuantity = productRepo.getAvailableProducts(product);
			if(orderedQuantity > availableQuantity) {
				throw new InsufficientProductsException();
			}
			productRepo.orderProduct(product, orderedQuantity);
			transactionStatus = true;
			return transactionStatus;
		}

}
