package com.olify.eprice.microservice.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.accounts.AccountsNotFoundException;
import com.olify.eprice.microservice.accounts.util.MoneyTransferGatewayUtils;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.model.InsufficientFundsException;
import com.olify.eprice.microservice.repository.AccountsRepository;

@Service
public class AccountsRegistrar {
	@Autowired
	private AccountsRepository accountsRepository;
	
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

	public Accounts deleteAccount(Accounts account) {
		return accountsRepository.deleteAccount(account);		
	}

	public Accounts createNewAccount(Accounts accounts) {
		return accountsRepository.createNewAccount(accounts);
	}

	public Accounts updateAccount(Accounts accounts) {
		return accountsRepository.updateAccount(accounts);
	}

	public boolean removeAccount(Accounts accounts) {
		accountsRepository.delete(accounts);
		return true;
	}

	public List<Accounts> findAllAccounts() {
		return accountsRepository.findAll();
	}

	public double getBalance(double amount) {
		return accountsRepository.findByAmount(amount);
		
	}

	public void deposit(Accounts account, double amount) throws InsufficientFundsException {
		account.setBalance(account.getBalance() + amount);
		accountsRepository.updateAccount(account);
	}

	public void transferTo(double balance, Long fromAccountNo, Long toAccountNo) throws InsufficientFundsException {
		Accounts from = accountsRepository.findByAccountNo(fromAccountNo);
		Accounts to = accountsRepository.findByAccountNo(toAccountNo);
		
		Double transactionFee = MoneyTransferGatewayUtils.TRANSFER_TRANSACTION_FEE;
		
		if(from == null || to == null)
			throw new InsufficientFundsException("One of the accounts doesn't exist!");
		if(!from.getAccountType().equals(to.getAccountType()))
			throw new InsufficientFundsException("The account type must be the same in order to have a successfull transfer between accounts");
		
		if(from.getBalance() >= (balance+transactionFee)) {
			withdraw(from, (balance+transactionFee));
			deposit(to, balance);
		} else
			throw new InsufficientFundsException("The account does not have sufficient balance to transfer money");
	}

	public void withdraw(Accounts account, Double amount) throws InsufficientFundsException {
		if(account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			accountsRepository.updateAccount(account);
		} else
			throw new InsufficientFundsException("The account does not have sufficient balance");
	}

	public Accounts findByAccountNo(Long accountNo) {
		return accountsRepository.findByAccountNo(accountNo);
	}
}