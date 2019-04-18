/*
 * This class contains configuration variables
 */
package com.olify.eprice.microservice.model;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {
	@Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
    
    public JwtConfig() {
    	
    }
    
	public JwtConfig(String uri, String header, String prefix, int expiration, String secret) {
		super();
		Uri = uri;
		this.header = header;
		this.prefix = prefix;
		this.expiration = expiration;
		this.secret = secret;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(Uri).append(header).append(prefix).append(expiration).append(secret);
		return builder.toString();
	}
}