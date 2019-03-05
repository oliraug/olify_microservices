package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.markets.Component.MarketsRepositoryImpl;
import com.olify.eprice.microservice.markets.Controller.MarketsController;
import com.olify.eprice.microservice.markets.Model.Markets;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MarketsControllerTest {
	@InjectMocks
	MarketsController marketsController;
	@Mock //this fixes our NullPointerException when testing the controller
	MarketsRepositoryImpl marketsRepositoryImpl;
	Markets markets;
	public static final String MARKET_A ="market_A";
	public static final String MARKET_B ="market_B";
	
	@Before
	public void setUp() throws Exception {
		markets = mock(Markets.class);
	}

	@After
	public void tearDown() throws Exception {
		marketsController = null;
		marketsRepositoryImpl = null;
		markets = null;
	}
/*
 * Method to get market by id
 */
	@Test
	public void test_shouldReturnMarketWhenGetMarketByIdIsCalled() throws Exception {
		markets = new Markets();
		markets.setId(1l);
		when(marketsRepositoryImpl.findOne(1l)).thenReturn(markets);
		Markets market = marketsController.getMarketsById(1L);
		verify(marketsRepositoryImpl).findOne(1l);
		assertThat(market.getId().longValue()).isEqualTo(1L);
	}
	
	//Method to create new market
	@Test
	public void test_shouldCreateNewMarketWhenCreateMarketIsCalled() throws Exception {
		markets = new Markets();
		markets.setMarketName(MARKET_A);
		when(marketsRepositoryImpl.createNewMarket(markets)).thenReturn(markets);
		marketsController.createMarket(markets);
		verify(marketsRepositoryImpl).createNewMarket(markets);
		assertThat(markets.getMarketName()).isEqualTo(MARKET_A);
	}
	
	//Method to update a market
	@Test
	public void test_shouldUpdateMarketWhenUpdateMarketByIdIsCalled() throws Exception {
		markets = new Markets();
		markets.setId(1l);
		markets.setMarketName(MARKET_B);
		when(marketsRepositoryImpl.findOne(1l)).thenReturn(markets);
		marketsController.updateMarket(1l, markets);
		verify(marketsRepositoryImpl, atLeastOnce()).saveMarket(markets);
		assertThat(markets.getMarketName()).isEqualTo(MARKET_B);
	}
	
	//Method to delete a market
	@Test
	public void test_shouldCallDeleteMarketMethodOfMarketsRepositoryImplWhenDeleteMarketIsCalled() throws Exception {
		doNothing().when(marketsRepositoryImpl).deleteMarket(markets);
		
		marketsController.deleteMarket(1l);
		verify(marketsRepositoryImpl, times(1)).deleteMarket(markets);
	}
}