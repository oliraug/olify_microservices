package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.markets.Enums.MarketStatus;
import com.olify.eprice.microservice.markets.Enums.ProductIntention;
import com.olify.eprice.microservice.markets.Enums.ProductMeasures;
import com.olify.eprice.microservice.markets.Enums.ProductStatus;
import com.olify.eprice.microservice.markets.Model.Markets;
import com.olify.eprice.microservice.markets.Model.OlifyProduct;
import com.olify.eprice.microservice.markets.Model.User;
import com.olify.eprice.microservice.markets.Repository.MarketsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=MarketsApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MarketsRepositoryTest {
	@Autowired private MarketsRepository marketsRepository;
	@Autowired private TestEntityManager entityManager;
	private User user = new User();
	private OlifyProduct product = new OlifyProduct("pineapples", 3000.00, 120, 120, 80, 40, ProductMeasures.PIECES, ProductStatus.ACTIVE, ProductIntention.SELLING, new Date(), "olify");
	@Before
	public void setUp() throws Exception {
	}

	@After 
	public void tearDown() throws Exception {
		entityManager.clear();
		marketsRepository.flush();
	}
	
	@Test
	public void test_shouldFindNoMarketsIfRepositoryIsEmpty() throws Exception {
		Iterable<Markets> market = marketsRepository.findAll();
		
		assertThat(market).isEmpty();
	}
	
	@Test
	public void test_whenSaveMarketShouldReturnFindByMarketName() throws Exception {
		marketsRepository.save(new Markets("Nakawa Market", MarketStatus.ACTIVE, user, "Nakawa", product, "Uganda", new Date(), new Date()));		
		
		assertThat(marketsRepository.findByMarketName("Nakawa Market")).isNotNull();
	}
	
	@Test
	public void test_getMarketReturnsMarketDetails() throws Exception {
		Markets savedMarkets = entityManager.persistFlushFind(new Markets("Nakasero Market", MarketStatus.ACTIVE, user, "Dastur Street,Nakasero", product, "Uganda", new Date(), new Date()));
		Markets markets = marketsRepository.findByMarketName("Nakasero Market");
		
		assertThat(markets.getMarketName()).isEqualTo(savedMarkets.getMarketName());
	}
	
	@Test
	public void test_shouldDeleteAllMarkets() throws Exception {
		entityManager.persist(new Markets("St.Balikudembe Market", MarketStatus.ACTIVE, user, "Nakivubo Road, Kampala", product, "Uganda", new Date(), new Date()));
		entityManager.persist(new Markets("Kibuye Market", MarketStatus.ACTIVE, user, "Kibuye,Kampala", product, "Uganda", new Date(), new Date()));
		
		marketsRepository.deleteAll();
		
		assertThat(marketsRepository.findAll()).isEmpty();
	}
	
	@Test
	public void test_shouldFindAllMarkets() throws Exception {
		Markets market = new Markets("Wandegeya Market", MarketStatus.ACTIVE, user, "Wandegeya", product, "Uganda", new Date(), new Date());
		entityManager.persist(market);
		Markets marketOne = new Markets("Kalerwe Market", MarketStatus.ACTIVE, user, "Kalerwe", product, "Uganda", new Date(), new Date());
		entityManager.persist(marketOne);
		Markets marketTwo = new Markets("Nakulabye Market", MarketStatus.ACTIVE, user, "Nakulabye", product, "Uganda", new Date(), new Date());
		entityManager.persist(marketTwo);
		
		Iterable<Markets> markets = marketsRepository.findAll();
		assertThat(markets).hasSize(3).contains(market, marketOne, marketTwo);
	}	
}