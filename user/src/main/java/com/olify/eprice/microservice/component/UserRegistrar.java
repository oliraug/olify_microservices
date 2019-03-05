/**
 * 
 */
package com.olify.eprice.microservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.model.OlifyUser;
import com.olify.eprice.microservice.repository.OlifyUserRepository;
import com.olify.eprice.microservice.user.UserNotFoundException;

/**
 * @author Olify
 *
 */
@Service
@Component
public class UserRegistrar {
	@Autowired
	private OlifyUserRepository userRepo;

	public UserRegistrar(OlifyUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Cacheable("users")
	public OlifyUser getUserDetails(String name) {
		OlifyUser user = userRepo.findByName(name);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

}