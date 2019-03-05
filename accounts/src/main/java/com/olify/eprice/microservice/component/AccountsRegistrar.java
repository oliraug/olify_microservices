package com.olify.eprice.microservice.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.accounts.AccountsNotFoundException;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.repository.AccountsRepository;

@Service
@Component
public class AccountsRegistrar {
	@Autowired
	private AccountsRepository accountsRepository;
	
	public AccountsRegistrar(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}
	
	public Accounts getAccountDetails(String accountName) {
		Accounts accounts = accountsRepository.getAccountDetails(accountName);
		if(accounts == null) {
			throw new AccountsNotFoundException();
		}
		return accounts;
	}

	public Accounts findByAccountName(String accountname) {
		return accountsRepository.findOne(accountname);
	}

	public Accounts save(Accounts account) {
		return accountsRepository.save(account);
	}

	public Accounts findOne(Long id) {
		return accountsRepository.getOne(id);
	}

	public void delete(Accounts account) {
		accountsRepository.delete(account);		
	}

	public Accounts createNewAccount(Accounts accounts) {
		return accountsRepository.createNewAccount(accounts);
	}

	public Accounts updateAccount(Accounts accounts) {
		return accountsRepository.updateAccount(accounts);
	}

	public boolean removeAccount(Accounts accounts) {
		return true;
	}

	public List<Accounts> findAll(Long id) {
		return accountsRepository.findAll();
	}

}