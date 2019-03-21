package com.olify.eprice.microservice.banks;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.banks.component.BankRepositoryImpl;
import com.olify.eprice.microservice.banks.controller.BankController;
import com.olify.eprice.microservice.banks.model.Bank;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(BankController.class)
@SpringBootTest(classes=BanksApplication.class, webEnvironment =SpringBootTest.WebEnvironment.MOCK )
@AutoConfigureMockMvc
public class BankControllerTest {
	private static final String BASE_URL = "olify/banks/Equity";
	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private BankController mockBankController;
	@Mock
	private BankRepositoryImpl mockBankRepositoryImpl;

	/*@Before
	public void setUp() throws Exception {
	}*/

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getBanks_ShouldReturnBanks() throws Exception {
		
		String json = "{\"bankname\":\"Equity\",\"bankcode\":\"Eq001\", \"bankstreet\":\"Buganda Road\","
				+ "\bankzip\":\"256\", \"bankcity\":\"Kampala\", \"bankstate\":\"Central\","
				+ "\"bankphone\":\"0414250897\", \"bankemail\":\"info@equity.ug\", \"bankcountry\":\"Uganda\","
				+ "\"bankstatus\":\"Active\"}";
		given(mockBankRepositoryImpl.getBankDetails(anyString())).willReturn(
				new Bank("Equity", "EQ0011", "Buganda Road", "256", 
						"Kampala", "Central", "0414250897", "", "info@equity.ug", "Uganda", "Active"));
		mockMvc.perform(get(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(json));
		verify(mockBankRepositoryImpl, atLeastOnce()).getBankDetails(json);
	}
	
	@Test
	public void test_shouldCreateANewBank() throws Exception {
		String json = "[{\"bankname\":\"Equity\",\"bankcode\":\"Eq001\", \"bankstreet\":\"Buganda Road\","
				+ "\bankzip\":\"256\", \"bankcity\":\"Kampala\", \"bankstate\":\"Central\","
				+ "\"bankphone\":\"0414250897\", \"bankemail\":\"info@equity.ug\", \"bankcountry\":\"Uganda\","
				+ "\"bankstatus\":\"Active\"}]";
		mockMvc.perform(post("/olify/banks")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isCreated());
	}
}