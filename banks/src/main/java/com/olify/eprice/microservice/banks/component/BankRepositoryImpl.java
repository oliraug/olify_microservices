package com.olify.eprice.microservice.banks.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olify.eprice.microservice.banks.model.Bank;
import com.olify.eprice.microservice.banks.repository.BankRepository;
import com.olify.eprice.microservice.banks.repository.BankService;

@Service
public class BankRepositoryImpl implements BankService{
	@Autowired
    private BankRepository bankRepository;

	@Override
	public Bank getBankDetails(String bankname) {
		
		return null; //bankRepository.findOne();
	}
	
	@Override
	public Bank saveBank(int id, Bank bank) {
		Bank banks = bankRepository.findById(bank.getId()).orElse(null);
		if(banks == null) {
			throw new IllegalArgumentException("Unable to find bank");
		}
		Bank saved = bankRepository.save(bank);
		return saved;
	}

	@Override
	public List<Bank> getBanksById(int id) {
		
		return null;
	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> banks = (List<Bank>) bankRepository.findAll();
		return banks;
	}

}
