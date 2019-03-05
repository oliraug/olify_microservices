package com.olify.eprice.microservice.markets.Repository;

import java.util.List;

import com.olify.eprice.microservice.markets.MarketsNotFoundException;
import com.olify.eprice.microservice.markets.Model.Markets;

public interface MarketsRepositoryCustom {
	Markets findByMarketName(String marketName);
	Markets createNewMarket(Markets markets);
	Markets updateMarket(Markets markets); 
	void deleteMarket(Markets markets);
	Markets findOne(long id);
	List<Markets> getAllMarkets();
	public Markets getMarketDetails(String marketName) throws MarketsNotFoundException;
	public Markets saveMarket(Markets market);
	
}