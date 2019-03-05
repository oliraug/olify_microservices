/**
 * 
 */
package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olify.eprice.microservice.model.Accounts;

/**
 * @author Olify
 *
 */
@RepositoryRestResource(collectionResourceRel = "olify_accounts", path = "olify_accounts")
public interface AccountsRepository extends JpaRepository<Accounts, Long>{

	Accounts findByAccountName(String accountname);

	Accounts getAccountDetails(String accountName);

	Accounts createNewAccount(Accounts accounts);

	Accounts updateAccount(Accounts accounts);

	Accounts findOne(String accountName);
}