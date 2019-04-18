/**
 * 
 */
package com.olify.eprice.microservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.model.OlifyCustomer;

/**
 * @author Olify
 *
 */
@Repository
public interface OlifyCustomerRepository extends JpaRepository<OlifyCustomer, Long>{

	Optional<Integer> forCustomer(String customerName);

	List<OlifyCustomer> findByCustomerName(String customerName);

	OlifyCustomer findAllById(Long customerId);
}