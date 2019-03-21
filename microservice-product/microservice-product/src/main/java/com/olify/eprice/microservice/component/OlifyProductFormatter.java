/**
 * class to convert string to another java type and back
 */

package com.olify.eprice.microservice.component;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.olify.eprice.microservice.model.OlifyProduct;
import com.olify.eprice.microservice.repository.OlifyProductRepository;

/**
 * @author Olify
 *
 */
@Component
public class OlifyProductFormatter implements Formatter<OlifyProduct> {
	
	private OlifyProductRepository productRepository;

	@Autowired
	public OlifyProductFormatter(OlifyProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public String print(OlifyProduct olifyProduct, Locale locale) {
		return olifyProduct.getId().toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 * method to do conversion from string to Date
	 */
	@Override
	public OlifyProduct parse(String text, Locale locale) throws ParseException {
		return productRepository.findById(Long.parseLong(text)).get();
	}
}