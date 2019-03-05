package com.olify.eprice.microservice.markets.Component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.markets.MarketsNotFoundException;
import com.olify.eprice.microservice.markets.Model.Markets;
import com.olify.eprice.microservice.markets.Repository.MarketsRepository;

@Service
public class MarketsRegistrar {
	private final Logger log = LoggerFactory.getLogger(MarketsRegistrar.class);
	@Autowired
	private MarketsRepository marketsRepository;
	
	public Markets createNewMarket(Markets markets) {
		log.info("Creating new market!");
		return marketsRepository.save(markets);
	}

	public Markets findByMarketName(String marketName) {
		return marketsRepository.findByMarketName(marketName);
	}

	public Markets updateMarket(Markets markets) {
		return marketsRepository.save(markets);
	}

	public void delete(Markets markets) {
		marketsRepository.delete(markets);		
	}

	public Markets findOne(long id) {
		return marketsRepository.getOne(id);
	}

	public List<Markets> getAllMarkets() {
		return marketsRepository.findAll();
	}
	
	public Markets getMarketDetails(String marketName) throws MarketsNotFoundException {
		Markets markets = marketsRepository.findByMarketName(marketName);
		if(markets == null) {
			throw new MarketsNotFoundException();
		}
		return markets;
	}

	public Markets save(Markets market) {
		return marketsRepository.save(market);
	}
}