/**
 * 
 */
package com.olify.eprice.microservice.order.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Olify
 *
 */
@Component
@Profile("test")
public class KafkaListenerBean {
	public static Logger logger = LoggerFactory.getLogger(KafkaListenerBean.class);

	private int received = 0;

	@KafkaListener(topics = "olifyorder", groupId = "orders")
	public void listen(ConsumerRecord<?, ?> cr) throws Exception {
		logger.info(cr.toString());
		logger.info(cr.value().toString());
		received++;
	}

	public int getReceived() {
		return received;
	}

}