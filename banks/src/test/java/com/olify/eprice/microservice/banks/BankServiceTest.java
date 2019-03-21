package com.olify.eprice.microservice.banks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.banks.component.BankRepositoryImpl;
import com.olify.eprice.microservice.banks.model.Bank;
import com.olify.eprice.microservice.banks.repository.BankRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankServiceTest {
	@Mock
	private BankRepository bankRepository;
	@InjectMocks
	private BankRepositoryImpl mockBankRepositoryImpl;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_shouldSaveDataInBank() throws Exception {
		Optional<Bank> optionalBook = Optional.of(createBankEntity());
		
		when(bankRepository.findById(1)).thenReturn(optionalBook);
        when(bankRepository.save(createBankEntity())).thenReturn(createBankEntity());

        Bank savedBank = mockBankRepositoryImpl.saveBank(1, createBankEntity());
        assertThat(savedBank).isNotNull();
	}
	
	@Test
    public void test_shouldGetAllBanks() throws Exception{
        when(bankRepository.findAll()).thenReturn(Arrays.asList(createBankEntity()));
        List<Bank> expectedBanks = mockBankRepositoryImpl.getAllBanks();
        
        assertThat(expectedBanks).isNotNull();
    }

	private Bank createBankEntity() {
		Bank banks = new Bank();
		banks.setId(1);
		banks.setBankName("Equity");
		return banks;
	}

}