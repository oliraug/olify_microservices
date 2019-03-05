package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.repository.AccountsRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountsRegistrarTest {
	private Accounts mockAccounts;
	@Mock
	private AccountsRepository accountsRepo;
	@Mock
	private AccountsRegistrar mockRegistrar;
	
	@Before
	public void setUp() throws Exception {
		mockRegistrar = mock(AccountsRegistrar.class);
		//mockAccounts = mock(Accounts.class);
	}

	@After
	public void tearDown() throws Exception {
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
		mockAccounts = new Accounts();
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
		assertThat(mockAccounts.getAccountName()).isEqualTo(newAccountInserted.getAccountName());
	}
	
	@Test
	public void test_updateAccount() throws Exception {
		//The old account (assumed that this came from a database or mock)
		Accounts oldAccount = new Accounts();
		oldAccount.setAccountName("Masiga Moses");
		oldAccount.setAccountType("current");
		
		//Check if the account is still the same. it is expected to be different since we updated it.
		String name = oldAccount.getAccountName();
		Accounts expectedAccount = new Accounts();
		expectedAccount = mockRegistrar.updateAccount(oldAccount);
		assertThat(expectedAccount, isA(Accounts.class));
		assertThat(name).isNotEqualTo(expectedAccount.getAccountName());		
	}
	
	@Test
	public void test_removeAnAccount() throws Exception {
		//setup account to be removed
		Accounts toBeRemovedAccount = new Accounts();
		toBeRemovedAccount.setAccountName("Masiga Moses");
		toBeRemovedAccount.setAccountType("current");
		//remove the account
		assertTrue(mockRegistrar.removeAccount(toBeRemovedAccount));
	}
}