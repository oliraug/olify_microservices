package com.olify.eprice.microservice.security;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

public class TokenService {
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static final Cache restApiAuthTokenCache = CacheManager.getCache("restApiAuthTokenCache");
    public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;

    @Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
    public void evictExpiredTokens() {
        logger.info("Evicting expired tokens");
        restApiAuthTokenCache.evictExpiredElements();
    }

    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public void store(String token, Authentication authentication) {
        restApiAuthTokenCache.put(new Element(token, authentication));
    }

    public boolean contains(Object token) {
        return restApiAuthTokenCache.get(token) != null;
    }

    public Authentication retrieve(Object token) {
        return (Authentication) restApiAuthTokenCache.get(token).getObjectValue();
    }
}