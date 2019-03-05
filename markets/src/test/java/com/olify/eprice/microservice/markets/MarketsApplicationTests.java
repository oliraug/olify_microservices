package com.olify.eprice.microservice.markets;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarketsApplicationTests {
	@LocalServerPort
	private int port = 0;
	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void contextLoads() {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port,
				String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}