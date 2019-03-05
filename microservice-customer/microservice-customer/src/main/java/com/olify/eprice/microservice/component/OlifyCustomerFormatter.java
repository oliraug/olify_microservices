/**
 * 
 */
package com.olify.eprice.microservice.component;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.model.OlifyCustomer;
import com.olify.eprice.microservice.repository.OlifyCustomerRepository;

/**
 * @author Olify
 *
 */
@Service
@Component
public class OlifyCustomerFormatter implements Formatter<OlifyCustomer> {
	@Autowired
	OlifyCustomerRepository customerRepository;
	
	@Autowired
	public OlifyCustomerFormatter(OlifyCustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public String print(OlifyCustomer customer, Locale local) {
		return customer.getCustomerId().toString();
	}

	@Override
	public OlifyCustomer parse(String text, Locale locale) throws ParseException {
		return customerRepository.findById(Long.parseLong(text)).get();
	}
}