/**
 * 
 */
package com.olify.eprice.microservice.security;

import java.util.Optional;
import java.lang.Class;

import org.apache.http.auth.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;

/**
 * @author Olify
 *
 */
public class TokenAuthenticationProvider implements AuthenticationProvider{
	private TokenService tokenService;
	
	public TokenAuthenticationProvider(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@SuppressWarnings("rawtypes")
	public Authentication authenticate(Authentication authentication) {
		Optional token = (Optional) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        if (!tokenService.contains(token.get())) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return tokenService.retrieve(token.get());
	}
	
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}