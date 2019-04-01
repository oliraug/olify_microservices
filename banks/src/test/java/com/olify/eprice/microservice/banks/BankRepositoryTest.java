package com.olify.eprice.microservice.banks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.olify.eprice.microservice.banks.model.Bank;
import com.olify.eprice.microservice.banks.repository.BankRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes=BanksApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BankRepositoryTest {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private TestEntityManager entityManager;
	String bankName = "Equity";
	String bankCode = "KCBUG";
	String bankStreet = "Kampala Road, Church House - Kampala Road";
	String bankZip = "256";
	String bankCity = "Kampala";
	String bankState = "Central";
	String bankPhone = "0414111213";
	String bankFax = "";
	String bankEmail = "info@ug.kcb.com";
	String bankCountry = "Uganda";
	String bankStatus = "Active";
	@Before
	public void setUp() throws Exception {
	}

	@After 
	public void tearDown() throws Exception {
		entityManager.clear();
		bankRepository.flush();
	}

	@Test
	public void test_getBankReturnsBankDetails() throws Exception {
		Bank savedBank = entityManager.persistFlushFind(new  Bank(bankName, bankCode, bankStreet, bankZip,  bankCity, bankState, bankPhone, bankFax, bankEmail,
				bankCountry, bankStatus));
		Bank bank = bankRepository.findByBankName("Equity");
		
		assertThat(bank.getBankName()).isEqualTo(savedBank.getBankName());
	}
	
	@Test
	public void test_shouldSaveBank() throws Exception {
		Bank bank = bankRepository.findByBankName(bankName);
		bank.setBankName(bankName);
		Bank newBank = bankRepository.save(bank);
		
		assertThat(bank.getBankName()).isEqualTo(newBank.getBankName());
	}
}