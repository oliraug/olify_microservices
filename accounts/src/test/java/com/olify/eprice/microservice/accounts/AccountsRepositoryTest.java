package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.repository.AccountsRepository;;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccountsApplication.class)*/
@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountsRepositoryTest {
	@Mock
	AccountsRepository accountsRepository;
	private Accounts accounts;
	@Autowired	
	private AccountsRegistrar accountsRegistrar;
	
	private static String ACCOUNT_A_NAME = "accountA";

	@Before
	public void setUp() throws Exception {
		accountsRepository = mock(AccountsRepository.class);
		accountsRegistrar = mock(AccountsRegistrar.class);
		//accounts = new Accounts();
	}

	@Test
	public void test_findByAccountName() throws Exception {
		when(accountsRepository.findOne(ACCOUNT_A_NAME)).thenReturn(accounts);
		Accounts account = accountsRegistrar.findByAccountName(ACCOUNT_A_NAME);
		assertThat(accounts).isEqualTo(account);
	}	
	
	@Test
	public void test_createAccountUsesAccountsRepository() throws Exception {
		when(accountsRepository.save(accounts)).thenReturn(accounts);
		Accounts account = accountsRegistrar.createNewAccount(accounts);
		assertThat(accounts).isEqualTo(account);
		verify(accountsRegistrar, atLeastOnce()).createNewAccount(accounts);
	}
	
	@Test
	public void test_updateAccountsUsesAccountsRepository() throws Exception {
		when(accountsRepository.save(accounts)).thenReturn(accounts);
		Accounts account = accountsRegistrar.updateAccount(accounts);
		assertThat(accounts).isEqualTo(account);
		verify(accountsRegistrar, atLeastOnce()).updateAccount(accounts);
	}
	
	@Test
	public void test_deleteAccountUsesAccountsRepository() throws Exception {
		accountsRegistrar.delete(accounts);
		verify(accountsRepository, times(1)).delete(accounts);
	}
}