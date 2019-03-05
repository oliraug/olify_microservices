/**
 * 
 */
package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.model.InsufficientFundsException;

/**
 * @author Olify
 *
 */
public class AccountsTest {
	private Accounts account;
	@Mock
	private AccountsRegistrar mockAccountsRegistrar;
	
	@Before
	public void setUp() throws Exception {
		account = new Accounts();
		mockAccountsRegistrar = mock(AccountsRegistrar.class);
	}

	@Test
	public void test_shouldBeEmptyAfterCreation() throws Exception {
		// given is for preconditions 
		//account = new Accounts();
		// when is for actions
		double balance = account.getBalance();
		// then is for performing validations
		assertThat(0.00).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldAllowToCreditAccount() throws Exception {
		// given
		//account = new Accounts();
		// when
		account.deposit(12000.0);
		// then
		double balance = account.getBalance();
		assertThat(12000.0).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldAllowToDebitAccount() throws Exception {
		// given
		//account = new Accounts();
		// when
		account.deposit(12000.00);
		account.withdrawal(2000.00);
		// then
		double balance = account.getBalance();
		assertThat(10000.00).isEqualTo(balance);
	}
	
	@Test
	public void test_shouldCreateAnAccount() throws Exception {
		//Accounts account = new Accounts();
		
		//Define the return value of method getAccount()
		when(mockAccountsRegistrar.getAccountDetails(account.getAccountName())).thenReturn(account);
		mockAccountsRegistrar.createNewAccount(account);
		verify(mockAccountsRegistrar, atLeastOnce()).createNewAccount(account);
	}
	
	@Test
	public void test_shouldTransferMoneyFromOneAccountToAnother() throws InsufficientFundsException{
		Accounts account2 = new Accounts(5000.00);
		account.transfer(1000.00, account2);
		assertThat(account.getBalance()).isEqualTo(1000.00);
		assertThat(account2.getBalance()).isZero();
	} 
	
	@Test(expected=InsufficientFundsException.class)
	public void test_transferShouldThrowInsufficientFundsException() throws InsufficientFundsException {
		Accounts account2 = new Accounts(5000.00);
		account.transfer(5001.00, account2);
	}
}
