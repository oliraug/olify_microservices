package com.olify.eprice.microservice.markets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olify.eprice.microservice.markets.Model.Markets;

@RepositoryRestResource(collectionResourceRel = "markets", path = "markets")
public interface MarketsRepository extends JpaRepository<Markets, Long>, MarketsRepositoryCustom {
}