/**
 * 
 */
package com.olify.eprice.microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.olify.eprice.microservice.component.UserRegistrar;
import com.olify.eprice.microservice.model.OlifyUser;
import com.olify.eprice.microservice.user.UserNotFoundException;

/**
 * @author Olify
 *
 */
@RestController
public class UserController {
	private UserRegistrar userRegistrar;

	public UserController(UserRegistrar userRegistrar) {
		this.userRegistrar = userRegistrar;
	}
	
	@GetMapping("/users/{name}")
	public OlifyUser getUser(@PathVariable String name) {
		return userRegistrar.getUserDetails(name);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void userNotFound(UserNotFoundException ex) {
		
	}
}
