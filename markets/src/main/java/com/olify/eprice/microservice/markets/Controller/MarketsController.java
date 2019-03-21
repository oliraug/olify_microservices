package com.olify.eprice.microservice.markets.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.olify.eprice.microservice.markets.MarketsNotFoundException;
import com.olify.eprice.microservice.markets.Component.MarketsRepositoryImpl;
import com.olify.eprice.microservice.markets.Model.Markets;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "MARKETS", description = "Agricultural Markets")  //Swagger Annotation
@RestController
@EnableWebMvc
@ExposesResourceFor(Markets.class)
@RequestMapping(value="/olify", produces="application/json")
public class MarketsController {

	@Autowired private MarketsRepositoryImpl marketsRepositoryImpl;

	/*Get market by id*/
	@GetMapping(value="/markets/{id}")
	@ApiOperation(value = "Get market", notes = "Return market")
	public Markets getMarketsById(@PathVariable Long id) {
		return marketsRepositoryImpl.findOne(id);
	}
	
	/*To save a market*/
	@PostMapping("/markets")
	@ApiOperation(value = "Create a market", notes = "Return market")
	public Markets createMarket(@Valid @RequestBody Markets market) {
		return marketsRepositoryImpl.createNewMarket(market);
	}
	
	@PostMapping(value="/markets/{marketName}")
	@ApiOperation(value = "Get market by user", notes = "Return a page of markets")
	public Markets getAllMarkets(@PathVariable("marketName") String marketName) throws MarketsNotFoundException{
		return marketsRepositoryImpl.getMarketDetails(marketName);		
	}
	
	/*Update market by id*/
	@PutMapping("markets/{id}")
	@ApiOperation(value = "Update market", notes = "Return updated markets")
	public ResponseEntity<Markets> updateMarket(@PathVariable("id") Long id, @Valid @RequestBody Markets marketDetails){
		Markets market = marketsRepositoryImpl.findOne(id);
		if(market == null) {
			return ResponseEntity.notFound().build();
		}
		market.setMarketName(marketDetails.getMarketName());
		market.setMarketStatus(marketDetails.getMarketStatus());
		market.setProduct(marketDetails.getProduct());
		market.setUser(marketDetails.getUser());
		market.setCountry(marketDetails.getCountry());
		market.setLocation(marketDetails.getLocation());
		market.setCreatedAt(marketDetails.getCreatedAt());
		market.setUpdatedAt(marketDetails.getUpdatedAt());
		
		Markets updateMarket = marketsRepositoryImpl.saveMarket(market);
		return ResponseEntity.ok().body(updateMarket);		
	}
	
	/*Delete market*/
	@DeleteMapping("/markets/{id}")
	@ApiOperation(value = "Delete a market", notes = "")
	public ResponseEntity<Markets> deleteMarket(@PathVariable("id") Long id){
		Markets market = marketsRepositoryImpl.findOne(id);
		if(market == null) {
			return ResponseEntity.notFound().build();
		}
		marketsRepositoryImpl.deleteMarket(market);
		
		return ResponseEntity.ok().build();
	}
}