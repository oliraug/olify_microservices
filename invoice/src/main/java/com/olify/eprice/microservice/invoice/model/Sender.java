/**
 * 
 */
package com.olify.eprice.microservice.invoice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Olify
 *
 */
@Service
public class Sender {
	@Autowired
    private KafkaTemplate<String, OlifyInvoice> kafkaTemplate;
	
	public void send(String topic, OlifyInvoice payload) {
		System.out.println("sending data=" + payload);
		kafkaTemplate.send(topic, payload);
	}
}
