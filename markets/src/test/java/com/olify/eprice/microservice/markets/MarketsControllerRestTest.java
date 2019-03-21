package com.olify.eprice.microservice.markets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.markets.Component.MarketsRepositoryImpl;
import com.olify.eprice.microservice.markets.Controller.MarketsController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MarketsController.class)
public class MarketsControllerRestTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MarketsRepositoryImpl marketsRepositoryImpl;
	@InjectMocks
	private MarketsController mockMarketsController;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getMarkets_ShouldReturnMarkets() throws Exception{
		String json = "{\"marketname\":\"Nakawa\",\"value\":\"Nakawa Market\"}";
		//given(accountsRegistrar.getAccountDetails(anyString())).willReturn((List<Accounts>) new Accounts("Masiga Moses", null, null, null, 0, 0, 0, null, null, null, null));
	
		mockMvc.perform(post("/olify/markets")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
				/*.andExpect(jsonPath("$.marketName", is("market_A")))
				.andExpect(jsonPath("$.value", is("nakawa")))
				.andExpect(jsonPath("$.*", hasSize(3)));*/
	}

}
