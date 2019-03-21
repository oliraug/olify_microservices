package com.olify.eprice.microservice.banks.repository;

import java.util.List;

import com.olify.eprice.microservice.banks.model.Bank;

public interface BankService {
	Bank saveBank(int id, Bank bank);

    List<Bank> getBanksById(int id);

    List<Bank> getAllBanks();
    Bank getBankDetails(String bankName);
}