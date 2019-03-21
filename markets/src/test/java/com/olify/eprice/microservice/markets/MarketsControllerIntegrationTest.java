package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olify.eprice.microservice.markets.Model.Markets;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=MarketsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class MarketsControllerIntegrationTest {
	@LocalServerPort
	private int serverPort;
	//calling APIs --> Markets APIs
	RestTemplate template = new RestTemplate();

	@Test
	public void test_shouldListAllMarkets() throws Exception {
		//Arrange
		
		//Act
		ResponseEntity<Markets> response = template.getForEntity(marketURL(), Markets.class);
		
		//Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMarketName()).isEqualTo("Nakawa Market");
		assertThat(response.getBody().getMarketStatus()).isEqualTo("Active");
		
		
		/*ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.reader(response.getBody());
		
		assertThat(responseJson.isMissingNode()).isFalse();
		//assertThat(responseJson.toString()).isEqualTo(marketURL());*/
	}

	private String marketURL() {
		return "http://localhost:+ serverPort/olify/markets";
	} 
}