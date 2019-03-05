package com.olify.eprice.microservice.product.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.olify.eprice.microservice.component.OlifyCustomerRegistrar;
import com.olify.eprice.microservice.configuration.JavaConfiguration;
import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservicecustomer.MicroserviceCustomerApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MicroserviceCustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@ContextConfiguration(classes=JavaConfiguration.class)
public class OlifyCustomerWebIntegrationTest {
	@Autowired
	private OlifyCustomerRegistrar olifyCustomerRegistrar;
	
	@LocalServerPort
	@Value("${local.server.port}")
    private String serverPort;

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void IsCustomerReturnedAsHTML() {

		OlifyCustomer customerMasiga = olifyCustomerRegistrar.findByCustomerName("Masiga").get();

		String body = getForMediaType(String.class, MediaType.TEXT_HTML,
				olifyCustomerURL() + customerMasiga.getCustomerId() + ".html");

		assertThat(body, containsString("Masiga"));
		assertThat(body, containsString("<div"));
	}

	@Test
	public void testIsHTMLReturned() {
		String body = getForMediaType(String.class, MediaType.TEXT_HTML, olifyCustomerURL());

		assertThat(body, containsString("<div"));
	}

	private String olifyCustomerURL() {
		return "http://localhost:" + serverPort;
	}

	private <T> T getForMediaType(Class<T> value, MediaType mediaType, String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(mediaType));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<T> resultEntity = restTemplate.exchange(url, HttpMethod.GET, entity, value);

		return resultEntity.getBody();
	}

	@Test
	public void testIsCustomerReturnedAsJSON() {

		OlifyCustomer customerMasiga = olifyCustomerRegistrar.findByCustomerName("Masiga").get();

		String url = olifyCustomerURL() + "customer/" + customerMasiga.getCustomerId();
		OlifyCustomer body = getForMediaType(OlifyCustomer.class, MediaType.APPLICATION_JSON, url);

		assertThat(body).isEqualTo(customerMasiga);
	}

	@Test
	public void testIsCustomerListReturned() {

		Iterable<OlifyCustomer> customers = olifyCustomerRegistrar.findAll();
		assertTrue(
				StreamSupport.stream(customers.spliterator(), false).noneMatch(c -> (c.getCustomerName().equals("Hoeller1"))));
		ResponseEntity<String> resultEntity = restTemplate.getForEntity(olifyCustomerURL() + "/list.html", String.class);
		assertTrue(resultEntity.getStatusCode().is2xxSuccessful());
		String customerList = resultEntity.getBody();
		assertFalse(customerList.contains("Moses"));
		olifyCustomerRegistrar
				.save(new OlifyCustomer(1L, "Masiga Moses", "0773405024", "moses.masiga@facebook.com", "Mutungo", "active", null, 8585));

		customerList = restTemplate.getForObject(olifyCustomerURL() + "/list.html", String.class);
		assertTrue(customerList.contains("Moses"));
	}
	
	@Test
	public void IsCustomerFormDisplayed() {
		ResponseEntity<String> resultEntity = restTemplate.getForEntity(olifyCustomerURL() + "/form.html", String.class);
		assertTrue(resultEntity.getStatusCode().is2xxSuccessful());
		assertTrue(resultEntity.getBody().contains("<form"));
	}

	@Test
	@Transactional
	public void IsSubmittedCustomerSaved() {
		assertEquals(0, olifyCustomerRegistrar.findByCustomerName("moses"));
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("customerName", "Masiga Moses");
		map.add("customerMobile", "0773405024");
		map.add("customerEmail", "moses.masiga@facebook.com");
		map.add("customerAddress", "Mutungo");
		map.add("customerStatus", "active");

		restTemplate.postForObject(olifyCustomerURL() + "form.html", map, String.class);
		assertEquals(1, olifyCustomerRegistrar.findByCustomerName("MAsiga"));
	}
}
