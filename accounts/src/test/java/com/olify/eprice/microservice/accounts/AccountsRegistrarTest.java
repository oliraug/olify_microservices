package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

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
	private Accounts mockAccounts;
	@Mock
	private AccountsRepository accountsRepo;
	@InjectMocks
	private AccountsRegistrar mockRegistrar;
	private double amount;
	
	@Before
	public void setUp() throws Exception {
		mockRegistrar = mock(AccountsRegistrar.class);
		mockAccounts = mock(Accounts.class);
	}

	@After
	public void tearDown() throws Exception {
		mockAccounts = null;
		accountsRepo = null;
		mockRegistrar = null;
	}

	@Test
	public void test_getAccountDetails_shouldReturnAccountInfo() throws Exception{
		/*List<Accounts> accounts = Arrays.asList(new Accounts("Masiga", null, null, null, 0, 0, 0, null, null, null, null));
		given(accountsRepo.findByAccountName("Masiga")).willReturn(accounts);
		
		Accounts account = mockRegistrar.getAccountDetails("Masiga");
		assertThat(account).getAccountName()).isEqualTo("Masiga");*/
	}
	
	@Test
	public void test_createNewAccount() throws Exception {
		//mockAccounts = new Accounts();
		mockAccounts.setAccountName("Masiga Moses");
		mockAccounts.setAccountType("Current");
		mockAccounts.setBalance(10000.00);
		mockAccounts.setCredit(1000.00);
		mockAccounts.setDebit(0.00);
		mockAccounts.setAccountStatus("active");
		
		Accounts newAccountInserted = mockRegistrar.createNewAccount(mockAccounts);
		
		/*
		 * check if accounts has the same composition
		 */
		assertThat(mockAccounts, isA(Accounts.class));
		assertThat(mockAccounts).isEqualTo(newAccountInserted);
	}
	
	@Test
	public void test_updateAccount() throws Exception {
		//The old account (assumed that this came from a database or mock)
		Accounts oldAccount = new Accounts();
		oldAccount.setAccountName("Masiga Moses");
		oldAccount.setAccountType("current");
		
		Accounts expectedAccount = new Accounts();
		expectedAccount = mockRegistrar.updateAccount(oldAccount);
		assertThat(expectedAccount).isEqualTo(oldAccount);	
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
		assertThat(12000.0).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldTransferMoneyFromOneAccountToAnother() throws InsufficientFundsException{
		Accounts account = new Accounts(5000.00);
		mockRegistrar.transferTo(1000.00, account);
		assertThat(mockRegistrar.getBalance(amount)).isEqualTo(4000.00);
	} 
}