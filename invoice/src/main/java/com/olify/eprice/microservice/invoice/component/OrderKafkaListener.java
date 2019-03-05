package com.olify.eprice.microservice.invoice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.olify.eprice.microservice.invoice.model.OlifyInvoice;

@Component
public class OrderKafkaListener {
	private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

	private InvoiceRegistrar invoiceRegistrar;

	public OrderKafkaListener(InvoiceRegistrar invoiceRegistrar) {
		super();
		this.invoiceRegistrar = invoiceRegistrar;		
	}
	
	@KafkaListener(topics = "order")
	public void order(OlifyInvoice invoice, Acknowledgment acknowledgment) {
		log.info("Received Invoice", invoice.getId());
		invoiceRegistrar.generateInvoice(invoice);
		acknowledgment.acknowledge();
	}
}