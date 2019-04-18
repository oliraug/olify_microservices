package com.olify.eprice.microservice.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.olify.eprice.microservice.model.JwtConfig;

import io.micrometer.core.instrument.util.StringUtils;

//OncePerRequestFilter -> It guarantee a single execution per request 
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	
	private RestTemplate restTemplate;
	private final JwtConfig jwtConfig;
	
	public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException{
		//get the authentication header. Tokens are supposed to be passed in the authentication header
		String authToken = request.getHeader(jwtConfig.getHeader());
		
		if(!StringUtils.isEmpty(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", authToken);
				//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				
				HttpEntity<String> entity = new HttpEntity<String>("", headers);
				
				ResponseEntity<String> responseEntity = restTemplate.exchange(
						"http://localhost/users", 
						HttpMethod.POST,
						entity,
						String.class);
				String jsonUserDetails = responseEntity.getBody();
				//UserDetails userDetails = prepareUserDetails(jsonUserDetails);
				if(jsonUserDetails != null) {
					//List<String> authorities = responseEntity.
				}
			} catch(Exception ex) {
				// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
				SecurityContextHolder.clearContext();
			}
			// go to the next filter in the filter chain
			chain.doFilter(request, response);
		}
	}
}
