/**
 * 
 */
package com.olify.eprice.microservice.invoice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Olify
 *
 */
public class Sender {
	@Autowired
    private KafkaTemplate<String, OlifyInvoice> kafkaTemplate;
	
	public void send(String topic, OlifyInvoice payload) {
		kafkaTemplate.send(topic, payload);
	}
}
