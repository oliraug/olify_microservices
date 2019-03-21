package com.olify.eprice.microservice.banks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olify.eprice.microservice.banks.model.Bank;

public class BankTest {
	long id = 0L;
	String bankName = "KCB, Equity, Centenary, StanChart, Barclays, DFCU, Stanbic";
	String bankCode = "KCBUG, EQUG, CERUDB, SCUG, BARUG, DFCUUG, STANBICUG";
	String bankStreet = "Kampala Road, Church House - Kampala Road, Nile Avenue, Bombo Road -Wandegeya, Nakasero, Clement Hill Road";
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
	}
	
	private Bank createTestBankOne() {
		
		return new Bank(bankName, bankCode, bankStreet, bankZip,  bankCity, bankState, bankPhone, bankFax, bankEmail,
				bankCountry, bankStatus);
	}

	@Test
	public void testBankShouldFillInAllParameters() throws Exception {
				
		Bank toBank = new Bank(bankName, bankCode, bankStreet, bankZip,  bankCity, bankState, bankPhone, bankFax, bankEmail,
				bankCountry, bankStatus);
		assertEquals((long) toBank.getId(), 0L);
		assertThat(toBank.getBankName()).isEqualTo(bankName);
		assertThat(toBank.getBankCode()).isEqualTo(bankCode);
		assertThat(toBank.getBankStreet()).isEqualTo(bankStreet);
		assertThat(toBank.getBankCity()).isEqualTo(bankCity);
		assertThat(toBank.getBankState()).isEqualTo(bankState);
		assertThat(toBank.getBankPhone()).isEqualTo(bankPhone);
		assertThat(toBank.getBankFax()).isEqualTo(bankFax);
		assertThat(toBank.getBankEmail()).isEqualTo(bankEmail);
		assertThat(toBank.getBankCountry()).isEqualTo(bankCountry);
		assertThat(toBank.getBankStatus()).isEqualTo(bankStatus);
		assertThat(toBank.getBankZip()).isEqualTo(bankZip);
	}
	
	/**
	   * @see Bank#setBankNames(String)
	   */
	@Test
	public void testSetBank_ShouldSetMultipleBankNames() throws Exception {
		Bank testBank = createTestBankOne();
		String bankNames = "KCB, Equity, Centenary, StanChart, Barclays, DFCU, Stanbic";
		testBank.setBankName(bankNames);
		assertThat(testBank.getBankName()).isEqualTo(bankNames);
	}
	
	/**
	   * @see Bank#addBankName(String)
	   */
	@Test
	public void testAddBank_ShouldAddNewBank() throws Exception {
		Bank testBank = createTestBankOne();
		String newBank = "BOI";
		
		testBank.setBankName(newBank);
		assertThat(testBank.getBankName()).isEqualTo(newBank);
	}
}