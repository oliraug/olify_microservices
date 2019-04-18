package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.markets.Enums.MarketStatus;
import com.olify.eprice.microservice.markets.Model.Markets;
import com.olify.eprice.microservice.markets.Model.OlifyProduct;
import com.olify.eprice.microservice.markets.Model.User;

public class MarketsTest {
	long id = 1L;
	String marketName = "Nakasero, Nakawa, Wandegeya, Balikudembe, Kibuye";
	MarketStatus marketStatus = MarketStatus.ACTIVE;
	User user = new User();
	String location = "Dastur Street, Nakawa, Wandegeya, Owino, Kibuye";
	OlifyProduct product = new OlifyProduct();
	String country = "Uganda";
	Date createdAt = new Date(20/3/2019);
	Date updatedAt = new Date(20/3/2019);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

private Markets createTestMarketOne() {		
		return new Markets(marketName, marketStatus, user, location,  product, country, createdAt, updatedAt);
	}

	@Test
	public void testMarketShouldFillInAllParameters() throws Exception {
				
		Markets toMarkets = new Markets(marketName, marketStatus, user, location,  product, country, createdAt, updatedAt);
		
		assertThat(toMarkets.getMarketName()).isEqualTo(marketName);
		assertThat(toMarkets.getMarketStatus()).isEqualTo(marketStatus);
		assertThat(toMarkets.getUser()).isEqualTo(user);
		assertThat(toMarkets.getLocation()).isEqualTo(location);
		assertThat(toMarkets.getProduct()).isEqualTo(product);
		assertThat(toMarkets.getCountry()).isEqualTo(country);
		assertThat(toMarkets.getCreatedAt()).isEqualTo(createdAt);
		assertThat(toMarkets.getUpdatedAt()).isEqualTo(updatedAt);
	}
	
	/**
	   * @see Market#setMarketNames(String)
	   */
	@Test
	public void testSetMarket_ShouldSetMultipleMarketNames() throws Exception {
		Markets testMarket = createTestMarketOne();
		String marketNames = "Nakasero, Nakawa, Wandegeya, Balikudembe, Kibuye";
		testMarket.setMarketName(marketNames);
		assertThat(testMarket.getMarketName()).isEqualTo(marketNames);
	}
	
	/**
	   * @see Market#addMarketName(String)
	   */
	@Test
	public void testAddMarket_ShouldAddNewBank() throws Exception {
		Markets testMarket = createTestMarketOne();
		String newMarket = "Nateete Market";
		
		testMarket.setMarketName(newMarket);
		assertThat(testMarket.getMarketName()).isEqualTo(newMarket);
	}
}