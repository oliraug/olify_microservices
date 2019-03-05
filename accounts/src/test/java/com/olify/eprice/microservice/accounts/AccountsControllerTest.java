/**
 * 
 */
package com.olify.eprice.microservice.accounts;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.olify.eprice.microservice.component.AccountsRegistrar;
import com.olify.eprice.microservice.controller.AccountsController;

/**
 * @author Olify
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountsRegistrar accountsRegistrar;
	@InjectMocks
	private AccountsController mockAccountsController;
	
	@Before
	public void setUp() throws Exception {
		//mockMvc = MockMvcBuilders.standaloneSetup(mockAccountsController).build();
	}

	@After
	public void tearDown() throws Exception {
		mockMvc = null;
		accountsRegistrar = null;
	}

	@Test
	public void test_getAccounts_ShouldReturnAccounts() throws Exception{
		//given(accountsRegistrar.getAccountDetails(anyString())).willReturn((List<Accounts>) new Accounts("Masiga Moses", null, null, null, 0, 0, 0, null, null, null, null));
		mockMvc.perform(get("/olify/accounts/accountName")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountName", is("Masiga Moses")))
				.andExpect(jsonPath("$.value", is("Masiga Moses")))
				.andExpect(jsonPath("$.*", hasSize(3)));
	}

	@Test
	public void getUser_notFound() throws Exception{
		given(accountsRegistrar.getAccountDetails(anyString())).willThrow (new AccountsNotFoundException());
		mockMvc.perform(get("/accounts/accountName"))
		.andExpect(status().isNotFound());		
	}
}
