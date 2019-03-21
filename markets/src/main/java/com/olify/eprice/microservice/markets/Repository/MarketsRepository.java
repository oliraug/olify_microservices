package com.olify.eprice.microservice.markets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.olify.eprice.microservice.markets.Model.Markets;

@Transactional
@RepositoryRestResource(collectionResourceRel = "markets", path = "markets")
public interface MarketsRepository extends JpaRepository<Markets, Long> {
	@Query("SELECT market_name FROM olify_markets m WHERE m.market_name = :marketName")
	Markets findByMarketName(String marketName);
}