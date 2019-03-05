package com.olify.eprice.microserviceproduct;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.olify.eprice.microserviceproduct.MicroserviceProductApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=MicroserviceProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class MicroserviceProductApplicationTests {
	@LocalServerPort
    private int port = 0;
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void lastnLoads() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/lastn/",
                String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void eurekaLoads() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port,
                String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
	
	@Test
	public void contextLoads() {
		
	}

}