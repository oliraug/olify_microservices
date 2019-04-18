/**
 * 
 */
package com.olify.eprice.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.olify.eprice.microservice.model.JwtConfig;


/**
 * @author Olify
 *
 */
@Configuration
@EnableWebSecurity // Enable security config. This annotation denotes config for spring security.
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	private AuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private JwtConfig jwtConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	    //we don't need CSRF because our token is invulnerable
	            csrf().disable()
	            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
	            .and()
	         //make sure we use stateless session; session won't be used to store user's state.
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	            and().
	            authorizeRequests().
	            antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	            .antMatchers(HttpMethod.GET, "/products/**").permitAll()
	            .antMatchers(HttpMethod.GET, "/products/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
	            //authenticated requests
	            .anyRequest().authenticated();
	            
	 // Add a filter to validate the tokens with every request
	    http.addFilterBefore(new JwtTokenAuthenticationFilter(jwtConfig),  UsernamePasswordAuthenticationFilter.class);
	    
	    //Disable page caching
	    http.headers().cacheControl();
	}

	@Bean
	public JwtConfig jwtConfig() {
        return new JwtConfig();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}