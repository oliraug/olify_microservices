/**
 * 
 */
package com.olify.eprice.microservice.product.Component;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.product.InsufficientProductsException;
import com.olify.eprice.microservice.product.Model.Product;
import com.olify.eprice.microservice.product.Repository.ProductRepository;
import com.olify.eprice.microservice.product.Repository.ProductService;

/**
 * @author Olify
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductRepository productRepository;
		
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

		public List<Product> findAllProducts() {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<Product> productsList = session.createQuery("FROM product").list();
			session.close();
			return productsList;
		}
		
		public void deleteProduct(Long productId) {
			Session session = getSessionFactory().openSession();
			//creating transaction object
			Transaction tx = session.beginTransaction();
			Optional<Product> product = findById(productId);
			session.delete(product);
			//transaction is committed to database
			tx.commit();
			session.close();
			logger.info("Record is successfully deleted!=" + product.toString());
		
		}

		@SuppressWarnings("unchecked")
		private Optional<Product> findById(Long productId) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.eq("productId", productId));
			Optional<Product> product = (Optional<Product>) criteria.uniqueResult();
			session.close();
			return product;
		}
		
		//method used to update records to a database table		
		public Product saveOrUpdate(Product product) {
				Session session = getSessionFactory().openSession();
				
				//creating transaction object
				Transaction tx = session.beginTransaction();
				Product updateProduct = session.load(Product.class,product.getId());
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
				return productRepository.save(product);
			}
		
		public boolean isProductNameExist(String productname) {
			Session session = getSessionFactory().openSession();
			//Creating Transaction object
			Transaction transaction = session.beginTransaction();
			Product product = findByProductName(productname);
			session.contains(product);
			//Commit Transaction to database
			transaction.commit();
			//closing the session object
			session.close();
			return true;
		}

		public Long createProduct(Product product) {
			Session session = getSessionFactory().openSession();
			//Creating Transaction object
			Transaction transaction = session.beginTransaction();
			session.save(product);
			//Commit Transaction to database
			transaction.commit();
			//closing the session object
			session.close();
			logger.info("Successfully created"+ product.toString());
			
			return product.getId();
			
		}

		//Retrieving product by id
		public Product getProductById(Long id) {
			Session session = getSessionFactory().openSession();
			Product product = (Product) session.createQuery("FROM product WHERE product_id = :product_id");
			
			//closing the session object
			session.close();
			return product;
		}

		public Product saveProduct(Product product) {
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
		
		
		public boolean buy(Product product, int unitsOnOrder) throws InsufficientProductsException {
			boolean transactionStatus = false;
			//called getAvailableProducts() of OlifyProductRepository to check if sufficient quantity of the specified product is available
			int availableQuantity = productRepository.findByUnitsInStock(product);
			if(unitsOnOrder > availableQuantity) {
				throw new InsufficientProductsException();
			}
			//productRepository.findByProductNameAndUnitsOnOrder(product, unitsOnOrder);
			transactionStatus = true; 
			return transactionStatus;
		}

		public void delete(Product prod) {
			logger.info("product is deleted successfully...");
			productRepository.delete(prod);
		}

		public int getAvailableProducts(Product product) {
	
			return productRepository.findByUnitsInStock(product);
		}

		@Override
		public int findByProductNameAndUnitsOnOrder(String productName, int unitsOnOrder) {
			
			return productRepository.findByProductNameAndUnitsOnOrder(productName, unitsOnOrder);
		}

		public Product findByProductName(String productName) {
			return productRepository.findByProductName(productName);
		}

		@Override
		public Product getOne(long id) {
			Session session = getSessionFactory().openSession();
			Product product = (Product) session.createQuery("FROM olify_product WHERE product_name = :product_name");
			
			//closing the session object
			session.close();
			return product;
		}

		/*@Override
		public Product save(Product product) {
			logger.info("product is saved successfully...");
			return productRepository.save(product);
		}*/
}