/**
 * 
 */
package com.olify.eprice.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.model.Accounts;

/**
 * @author Olify
 *
 */
@Api(value = "ACCOUNTS", description = "Bank Accounts")  //Swagger Annotation
@RestController
@ExposesResourceFor(Accounts.class)
@RequestMapping(value="/olify", produces="application/json")
public class AccountsController {
	@Autowired
	private AccountsRegistrar accountsRegistrar;
	public AccountsController(AccountsRegistrar accountsRegistrar) {
		this.accountsRegistrar = accountsRegistrar;
	}
	
	/*To save an account*/
	@PostMapping("/accounts")
	@ApiOperation(value = "Create an account", notes = "Return an account")
	public Accounts createAccount(@Valid @RequestBody Accounts account) {
		return accountsRegistrar.save(account);
	}
	
	/*Get an account by name*/
	@GetMapping(value="/accounts/{id}")
	@ApiOperation(value = "Get an account", notes = "Return an account")
	public List<String> getAccounts(@PathVariable("id") Long id){
		return accountsRegistrar.findAll(id)
								.stream()
								.map(Accounts::getAccountName)
								.collect(Collectors.toList());
	}
	
	@PostMapping(value="/accounts/{accountName}")
	@ApiOperation(value = "Get accounts per user", notes = "Return a page of accounts")
	public Accounts getAllAccounts(@PathVariable("accountname") String accountName){
		return accountsRegistrar.getAccountDetails(accountName);		
	}
	
	/*Update an account by id*/
	@PutMapping("accounts/{id}")
	@ApiOperation(value = "Update an account", notes = "Return updated accounts")
	public ResponseEntity<Accounts> updateAccount(@PathVariable("accountid") Long id, @Valid @RequestBody Accounts accountDetails){
		Accounts account = accountsRegistrar.findOne(id);
		if(account == null) {
			return ResponseEntity.notFound().build();
		}
		account.setAccountName(accountDetails.getAccountName());
		account.setParent(accountDetails.getParent());
		account.setInternalType(accountDetails.getInternalType());
		account.setAccountType(accountDetails.getAccountType());
		account.setDebit(accountDetails.getDebit());
		account.setCredit(accountDetails.getCredit());
		account.setBalance(accountDetails.getBalance());
		account.setAccountStatus(accountDetails.getAccountStatus());
		account.setDefaultTaxes(accountDetails.getDefaultTaxes());
		account.setReconcillation(accountDetails.getReconcillation());
		account.setNotes(accountDetails.getNotes());
		
		Accounts updateAccount = accountsRegistrar.save(account);
		return ResponseEntity.ok().body(updateAccount);		
	}
	
	/*Delete an account*/
	@DeleteMapping("/accounts/{id}")
	@ApiOperation(value = "Delete an account", notes = "")
	public ResponseEntity<Accounts> deleteAccount(@PathVariable("accountid") Long id){
		Accounts account = accountsRegistrar.findOne(id);
		if(account == null) {
			return ResponseEntity.notFound().build();
		}
		accountsRegistrar.delete(account);
		
		return ResponseEntity.ok().build();
	}
}