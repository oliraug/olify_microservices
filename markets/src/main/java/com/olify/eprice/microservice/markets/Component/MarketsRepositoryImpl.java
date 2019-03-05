package com.olify.eprice.microservice.markets.Component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.markets.MarketsNotFoundException;
import com.olify.eprice.microservice.markets.Model.Markets;
import com.olify.eprice.microservice.markets.Repository.MarketsRepository;
import com.olify.eprice.microservice.markets.Repository.MarketsRepositoryCustom;

@Service
public class MarketsRepositoryImpl implements MarketsRepositoryCustom {
	EntityManager em;
	@Autowired MarketsRepository marketsRepository;
	@Override
	public Markets findByMarketName(String marketName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Markets> market = builder.createQuery(Markets.class); //SELECT market_name FROM markets
		
		Root<Markets> fromMarket = market.from(Markets.class); 
	
		market.select(fromMarket).where(builder.equal(fromMarket.get("marketName"), marketName)); //WHERE market_name
		TypedQuery<Markets> query = em.createQuery(market);
		Markets result = query.getSingleResult();
		return result;
	}

	@Override
	public Markets createNewMarket(Markets markets) {
		return marketsRepository.save(markets);
	}

	@Override
	public Markets updateMarket(Markets markets) {
		return marketsRepository.save(markets);
	}

	@Override
	public void deleteMarket(Markets markets) {
		marketsRepository.delete(markets);
	}

	@Override
	public Markets findOne(long id) {
		return marketsRepository.findOne(id);
	}

	@Override
	public List<Markets> getAllMarkets() {
		return marketsRepository.findAll();
	}

	@Override
	public Markets getMarketDetails(String marketName) throws MarketsNotFoundException {
		Markets markets = marketsRepository.findByMarketName(marketName);
		if(markets == null) {
			throw new MarketsNotFoundException();
		}
		return markets;
	}

	@Override
	public Markets saveMarket(Markets market) {
		return marketsRepository.save(market);
	}
	
}