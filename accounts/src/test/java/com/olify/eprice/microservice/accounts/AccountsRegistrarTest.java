package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.model.InsufficientFundsException;
import com.olify.eprice.microservice.repository.AccountsRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountsRegistrarTest {
	@Mock
	private Accounts mockAccounts;
	@Mock
	private AccountsRepository accountsRepo;
	@InjectMocks
	private AccountsRegistrar mockRegistrar;
	private double amount;
	String accountName = "Masiga Moses";
	String parent = "A_PARENT"; 
	String internalType = "A_TYPE";
	String accountType = "SAVINGS_ACCOUNT";
	double debit = 0.00;
	double credit = 1000.00;
	double balance = 4000.00;
	String accountStatus = "ACTIVE";
	String defaultTaxes = "PAYE";
	String reconcillation = "RECONCILLATION";
	String notes = "ACCOUNT OPENING";
	private double actualBalance;
	private static String ACCOUNT_A_NAME = "accountA";
	
	@Before
	public void setUp() throws Exception {
		mockRegistrar = mock(AccountsRegistrar.class);
		mockAccounts = new Accounts(accountName, parent, internalType, accountType, debit, credit, balance, accountStatus, defaultTaxes, reconcillation, notes);
	}

	@After
	public void tearDown() throws Exception {
		mockAccounts = null;
		accountsRepo = null;
		mockRegistrar = null;
	}
	
	@Test
	public void test_createNewAccount() throws Exception {		
		mockRegistrar.createNewAccount(mockAccounts);
		
		/*
		 * check if accounts has the same composition
		 */
		assertThat(mockAccounts, isA(Accounts.class));
		assertThat(accountName).isEqualTo(mockAccounts.getAccountName());
	}
	
	@Test
	public void test_updateAccount() throws Exception {
		//The old account (assumed that this came from a database or mock)
		Accounts oldAccount = new Accounts();
		oldAccount.setAccountName("Masiga Moses");
		oldAccount.setAccountType("current");
		
		mockRegistrar.updateAccount(oldAccount);
		assertThat(accountName).isEqualTo(oldAccount.getAccountName());	
	}
	
	@Test
	public void test_removeAnAccount() throws Exception {
		//setup account to be removed
		Accounts toBeRemovedAccount = new Accounts();
		toBeRemovedAccount.setAccountName("Masiga Moses");
		toBeRemovedAccount.setAccountType("current");
		//remove the account
		assertThat(mockRegistrar.removeAccount(toBeRemovedAccount)).isTrue();
	}
	
	@Test
	public void test_AccountShouldBeEmptyAfterCreation() throws Exception {
		// given is for preconditions 
		//account = new Accounts();
		// when is for actions
		double balance = mockRegistrar.getBalance(amount);
		// then is for performing validations
		assertThat(0.00).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldAllowToCreditAccount() throws Exception {
		// given
		//account = new Accounts();
		// when
		mockRegistrar.deposit(mockAccounts, 12000.0);
		// then
		double balance = mockRegistrar.getBalance(amount);
		assertThat(actualBalance).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldTransferMoneyFromOneAccountToAnother() throws InsufficientFundsException{
		double tranferAmount = 1000.00;
		Accounts account = new Accounts(5000.00);
		mockRegistrar.transferTo(tranferAmount, account);
		assertThat(accountsRepo.getBalance(amount)).isEqualTo(actualBalance);
		verify(mockRegistrar,  atLeastOnce()).transferTo(tranferAmount, account);
	} 
	
	@Test
	public void test_findByAccountName() throws Exception {
		when(accountsRepo.findOne(ACCOUNT_A_NAME)).thenReturn(mockAccounts);
		Accounts account = mockRegistrar.findByAccountName(ACCOUNT_A_NAME);
		assertThat(mockAccounts).isEqualTo(account);
	}	
	
	@Test
	public void test_createAccountUsesAccountsRepository() throws Exception {
		when(accountsRepo.save(mockAccounts)).thenReturn(mockAccounts);
		Accounts account = mockRegistrar.createNewAccount(mockAccounts);
		assertThat(mockAccounts).isEqualTo(account);
		verify(mockRegistrar, atLeastOnce()).createNewAccount(mockAccounts);
	}
	
	@Test
	public void test_updateAccountsUsesAccountsRepository() throws Exception {
		when(accountsRepo.save(mockAccounts)).thenReturn(mockAccounts);
		Accounts account = mockRegistrar.updateAccount(mockAccounts);
		assertThat(mockAccounts).isEqualTo(account);
		verify(mockRegistrar, atLeastOnce()).updateAccount(mockAccounts);
	}
	
	@Test
	public void test_deleteAccountUsesAccountsRepository() throws Exception {
		mockRegistrar.delete(mockAccounts);
		verify(mockRegistrar, times(1)).delete(mockAccounts);
	}
}