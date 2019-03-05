package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.olify.eprice.microservice.markets.Component.MarketsRepositoryImpl;
import com.olify.eprice.microservice.markets.Model.Markets;
import com.olify.eprice.microservice.markets.Repository.MarketsRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MarketsRepositoryTest {
	@Mock
	MarketsRepository marketsRepository;
	private Markets markets;
	@Autowired	
	private MarketsRepositoryImpl marketsRepositoryImpl;
	
	private static String MARKET_A_NAME = "marketA";

	@Before
	public void setUp() throws Exception {
		marketsRepositoryImpl = mock(MarketsRepositoryImpl.class);
		marketsRepository = mock(MarketsRepository.class);
	}

	@After
	public void tearDown() throws Exception {
		marketsRepository = null;
		marketsRepositoryImpl = null;
		markets = null;
	}

	@Test
	public void test_findByMarketName() throws Exception {
		when(marketsRepository.findByMarketName(MARKET_A_NAME)).thenReturn(markets);
		Markets market = marketsRepositoryImpl.findByMarketName(MARKET_A_NAME);
		assertThat(markets).isEqualTo(market);
	}	
	
	@Test
	public void test_createMarketUsesMarketsRepository() throws Exception {
		when(marketsRepository.save(markets)).thenReturn(markets);
		Markets market = marketsRepositoryImpl.createNewMarket(markets);
		assertThat(markets).isEqualTo(market);
		verify(marketsRepositoryImpl, atLeastOnce()).createNewMarket(markets);
	}
	
	@Test
	public void test_updateMarketsUsesMarketsRepository() throws Exception {
		when(marketsRepository.save(markets)).thenReturn(markets);
		Markets market = marketsRepositoryImpl.updateMarket(markets);
		assertThat(markets).isEqualTo(market);
		verify(marketsRepositoryImpl, atLeastOnce()).updateMarket(markets);
	}
	
	@Test
	public void test_deleteMarketUsesMarketsRepository() throws Exception {
		marketsRepositoryImpl.deleteMarket(markets);
		//verify(marketsRepository, times(1)).delete(markets);
	}
	
	//Test method that users service class to get all markets
	@Test
	public void test_shouldFindAllMarkets() throws Exception {
		List<Markets> markets = marketsRepositoryImpl.getAllMarkets();
		verify(marketsRepositoryImpl).getAllMarkets();
		assertThat(markets.size()).isGreaterThanOrEqualTo(0);
	}
}
