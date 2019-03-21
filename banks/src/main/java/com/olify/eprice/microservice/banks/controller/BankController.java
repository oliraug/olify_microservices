package com.olify.eprice.microservice.banks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.olify.eprice.microservice.banks.component.BankRepositoryImpl;
import com.olify.eprice.microservice.banks.model.Bank;

//@Api(value = "BANKS", description = "Customer Banking")  //Swagger Annotation
@RestController
@EnableWebMvc
@ExposesResourceFor(Bank.class)
@RequestMapping(value="/olify")
public class BankController {

	@Autowired
	private BankRepositoryImpl bankRepositoryImpl;

	@GetMapping("/banks/{bankname}")
	//@ApiOperation(value = "get banks", notes = "Return banks")
	public Bank getBank(@PathVariable String bankname) {
		return bankRepositoryImpl.getBankDetails(bankname);
	}
}
