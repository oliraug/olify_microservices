package com.olify.eprice.microservice.invoice;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.invoice.component.InvoiceRegistrar;
import com.olify.eprice.microservice.invoice.model.Address;
import com.olify.eprice.microservice.invoice.model.OlifyCustomer;
import com.olify.eprice.microservice.invoice.model.OlifyInvoice;
import com.olify.eprice.microservice.invoice.model.OlifyProduct;
import com.olify.eprice.microservice.invoice.repository.InvoiceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvoiceConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class InvoiceRegistrarTest {
	private InvoiceRepository invoiceRepository;
	private InvoiceRegistrar invoiceRegistrar;
	private OlifyInvoice invoice;

	@Before
	public void setUp() throws Exception {
		invoiceRepository = mock(InvoiceRepository.class);
		invoiceRegistrar = mock(InvoiceRegistrar.class);
	}

	@Test
	public void test_shouldGenerateAnInvoice() throws Exception{
		long countBefore = invoiceRepository.count();
		invoice = new OlifyInvoice(1L, new OlifyCustomer(1L, "Masiga", "Moses", "olify@olify.com", "0704008863"), new Date(2/19/19), new Address("Mutungo Bbiina", "+256", "Kampala"),
									   new OlifyProduct("Pineapples", 2500.00));
		invoiceRegistrar.generateInvoice(invoice);
		assertThat(invoiceRepository.count(),is(countBefore));
		//assertThat(invoiceRepository.findById(1L)).isEqualTo(0L);
		verify(invoiceRegistrar, atLeastOnce()).generateInvoice(invoice);
	}
}