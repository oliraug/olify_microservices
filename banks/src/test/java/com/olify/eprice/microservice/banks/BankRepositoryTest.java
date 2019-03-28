package com.olify.eprice.microservice.banks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.banks.model.Bank;
import com.olify.eprice.microservice.banks.repository.BankRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=BanksApplication.class)
//@Import(BanksApplication.class)
public class BankRepositoryTest {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Before
	public void setUp() throws Exception {
	}

	@After 
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getBankReturnsBankDetails() throws Exception {
		Bank savedBank = entityManager.persistFlushFind(new Bank("equity"));
		Bank bank = bankRepository.findByBankName("equity");
		
		assertThat(bank.getBankName()).isEqualTo(savedBank.getBankName());
	}
}