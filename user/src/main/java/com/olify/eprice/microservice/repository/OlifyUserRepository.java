/**
 * 
 */
package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.model.OlifyUser;

/**
 * @author Olify
 *
 */
@Repository
public interface OlifyUserRepository extends JpaRepository<OlifyUser, Long>{

	OlifyUser findByName(String name);
}