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
	public void test_whenSaveBankShouldReturnFindByBankName() throws Exception {
		bankRepository.save(new Bank(bankName, bankCode, bankStreet, bankZip,  bankCity, bankState, bankPhone, bankFax, bankEmail,
				bankCountry, bankStatus));		
		
		assertThat(bankRepository.findByBankName(bankName)).isNotNull();
	}
	
	@Test
	public void test_shouldFindNoBanksIfRepositoryIsEmpty() throws Exception {
		Iterable<Bank> banks = bankRepository.findAll();
		
		assertThat(banks).isEmpty();
	}
	
	@Test
	public void test_shouldDeleteAllBanks() throws Exception {
		entityManager.persist(new Bank("Centenary", "CENTUG", "Mapeera House,Kampala Road", "256", "Kampala", "Kampala Central", "0414707172", "0414707172", "info@centenary.co.ug", "Uganda", "Active"));
		entityManager.persist(new Bank("Kenya Commercial Bank", "KCBUG", "Commercial Plaza,Kampala Road", "256", "Kampala", "Kampala Central", "0414606172", "0414606172", "info@kcb.co.ug", "Uganda", "Active"));
		
		bankRepository.deleteAll();
		
		assertThat(bankRepository.findAll()).isEmpty();
	}
	
	@Test
	public void test_shouldFindAllBanks() throws Exception {
		Bank bank = new Bank("Centenary", "CENTUG", "Mapeera House,Kampala Road", "256", "Kampala", "Kampala Central", "0414707172", "0414707172", "info@centenary.co.ug", "Uganda", "Active");
		entityManager.persist(bank);
		Bank bankOne = new Bank("Equity", "EqUG", "Church House,Kampala Road", "256", "Kampala", "Kampala Central", "0414301172", "0414301172", "info@equity.co.ug", "Uganda", "Active");
		entityManager.persist(bankOne);
		Bank bankTwo = new Bank("Baroda", "BARUG", "Kampala Road", "256", "Kampala", "Kampala Central", "0414504172", "0414504172", "info@baroda.co.ug", "Uganda", "Active");
		entityManager.persist(bankTwo);
		
		Iterable<Bank> banks = bankRepository.findAll();
		assertThat(banks).hasSize(3).contains(bank, bankOne, bankTwo);
	}
}