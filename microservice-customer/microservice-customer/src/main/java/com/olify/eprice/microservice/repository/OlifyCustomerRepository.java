/**
 * 
 */
package com.olify.eprice.microservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.model.OlifyCustomer;

/**
 * @author Olify
 *
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "olify_product", path = "olify_product")
public interface OlifyCustomerRepository extends JpaRepository<OlifyCustomer, Long>{
	@Query("SELECT max(oc.updated_at) FROM olify_customer oc")
	Date lastUpdate();

	Optional<Integer> forCustomer(String customerName);

	List<OlifyCustomer> findByCustomerName(String customerName);

	OlifyCustomer findAllById(Long customerId);
}