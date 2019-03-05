package com.olify.eprice.microservicecustomer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=MicroserviceCustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class MicroserviceCustomerApplicationTests {
	@LocalServerPort
	@Value("${local.server.port}")
    private String serverport;
    private RestTemplate restTemplate = new RestTemplate();

    @Test
	public void contextLoads() { //Test that the context loads
	}
    
    @Test
    public void lastnLoads() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + serverport + "/lastn/",
                String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void eurekaLoads() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + serverport,
                String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}