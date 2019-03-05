/**
 * 
 */
package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olify.eprice.microservice.model.OlifyUser;

/**
 * @author Olify
 *
 */
@RepositoryRestResource(collectionResourceRel = "olify_user", path = "olify_user")
public interface OlifyUserRepository extends JpaRepository<OlifyUser, Long>{

	OlifyUser findByName(String name);
}