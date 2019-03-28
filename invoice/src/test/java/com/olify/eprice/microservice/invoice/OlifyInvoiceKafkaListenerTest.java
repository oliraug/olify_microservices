package com.olify.eprice.microservice.invoice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.invoice.model.Address;
import com.olify.eprice.microservice.invoice.model.OlifyCustomer;
import com.olify.eprice.microservice.invoice.model.OlifyInvoice;
import com.olify.eprice.microservice.invoice.model.OlifyProduct;
import com.olify.eprice.microservice.invoice.repository.InvoiceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=InvoiceConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)

public class OlifyInvoiceKafkaListenerTest {
	@Value("${jsa.kafka.topic}")
	private static String kafkaTopic = "olify-invoice-test";
	@Mock
	private InvoiceRepository invoiceRepository;
	//private OrderKafkaListener orderKafkaListener;

	@Autowired
	public KafkaTemplate<String, OlifyInvoice> kafkaTemplate;

	//@ClassRule
	public static EmbeddedKafkaBroker kafkaBroker = new EmbeddedKafkaBroker(1, true, kafkaTopic);

	@Before
	public void setUp() throws Exception {
		System.setProperty("spring.kafka.bootstrap-servers", kafkaBroker.getBrokersAsString());
	}

	@Test
	public void test_ordersAreReceived() throws Exception {
		Long countBefore = invoiceRepository.count();
		kafkaTemplate.send(kafkaTopic, new OlifyInvoice(1L, new OlifyCustomer(1L, "Masiga", "Moses", "olify@olify.com", "0704008863"), new Date(2/19/19), new Address("Mutungo Bbiina", "+256", "Kampala"),
									   new OlifyProduct(countBefore, "Pineapples", 2500.00, 0, 0, 0, 0, null, null, null, null, null)));
		int oo = 1;
		while(invoiceRepository.count() == countBefore && oo < 10) {
			Thread.sleep(1000);
			oo++;
		}
		assertThat(invoiceRepository.count()).isEqualTo(countBefore);
	} 

	/*private String olifyOrder() {
		return "{" +
				" \"id\" : 1, " +
				" \"customer\" : {" +
				"   \"customerId\" : 1L, " +
				"    \"firstname\" : Moses, " +
				"     \"lastname\" : Masiga, " +
				"      \"email\" : olify@olify.com, " +
				"       \"contact\" : +256773405024, " +
				"}" +
			"}";
	}*/

}
