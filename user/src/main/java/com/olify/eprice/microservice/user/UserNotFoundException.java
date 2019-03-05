/**
 * 
 */
package com.olify.eprice.microservice.user;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Olify
 *
 */
@ResponseStatus
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
