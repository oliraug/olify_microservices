/**
 * 
 */
package com.olify.eprice.microservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Olify
 *
 */
@Configuration
@EnableWebMvcSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	            csrf().disable().
	            sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	            and().
	            authorizeRequests().
	            antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
	            anyRequest().authenticated().
	            and().
	            anonymous().disable().
	            exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

	    http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
	            addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}
}