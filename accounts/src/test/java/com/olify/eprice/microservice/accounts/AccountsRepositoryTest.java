package com.olify.eprice.microservice.accounts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.model.Accounts;
import com.olify.eprice.microservice.repository.AccountsRepository;;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=AccountsApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class AccountsRepositoryTest {
	@Autowired AccountsRepository accountsRepository;
	@Autowired private TestEntityManager entityManager;
	

	@Before
	public void setUp() throws Exception {
	}
	@After 
	public void tearDown() throws Exception {
		entityManager.clear();
		accountsRepository.flush();
	}
	
	@Test
	public void test_shouldFindNoAccountsIfRepositoryIsEmpty() throws Exception {
		Iterable<Accounts> account = accountsRepository.findAll();
		
		assertThat(account).isEmpty();
	}

}