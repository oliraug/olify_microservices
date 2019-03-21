package com.olify.eprice.microservice.banks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.banks.model.Bank;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=BanksApplication.class, webEnvironment =SpringBootTest.WebEnvironment.DEFINED_PORT )

public class BankIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test_shouldReturnBankDetails() throws Exception {
		//act
		ResponseEntity<Bank> response = restTemplate.getForEntity("olify/banks/equity", Bank.class);
		//assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getBankName()).isEqualTo("equity");
		assertThat(response.getBody().getBankCode()).isEqualTo("eq001");
		assertThat(response.getBody().getBankStreet()).isEqualTo("Kampala Road");
	}

}
