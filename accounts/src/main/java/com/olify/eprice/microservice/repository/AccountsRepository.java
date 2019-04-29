/**
 * 
 */
package com.olify.eprice.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.model.Accounts;

/**
 * @author Olify
 *
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{

	Accounts findByAccountName(String accountname);

	Accounts getAccountDetails(String accountName);

	Accounts createNewAccount(Accounts accounts);

	Accounts updateAccount(Accounts accounts);

	Accounts findOne(String accountName);

	double findByAmount(double amount);

	void findByBalance(double amount);

	Accounts getAccount(Accounts accountNo);

	double getBalance(double amount);

	Accounts findByAccountNo(Long fromAccountNo);
	
	Accounts deleteAccount(Accounts Accounts);
}