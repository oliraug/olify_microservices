package com.olify.eprice.microservice.invoice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.invoice.model.OlifyInvoice;
import com.olify.eprice.microservice.invoice.model.Sender;
import com.olify.eprice.microservice.invoice.repository.InvoiceRepository;

@Service
public class InvoiceRegistrar {
	/*
	 * to send message to Kafka when a new invoice is saved in the database
	 */
	private final Logger log = LoggerFactory.getLogger(InvoiceRegistrar.class);
	@Value("${spring.kafka.topic.invoiceCreated}")
	private static String INVOICE_CREATED_TOPIC;
	@Autowired private InvoiceRepository invoiceRepository;
	private Sender sender;

	
	/*public InvoiceRegistrar(InvoiceRepository invoiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
	}*/

	public OlifyInvoice generateInvoice(OlifyInvoice invoice) {
		if(invoiceRepository.existsById(invoice.getId())) {
			log.info("Invoice id {} already exists - ignored", invoice.getId());
		} else {
			OlifyInvoice createdInvoice = invoiceRepository.save(invoice);
			sender.send(INVOICE_CREATED_TOPIC, createdInvoice);
			return createdInvoice;
		}
		return invoice;		
	}

}
