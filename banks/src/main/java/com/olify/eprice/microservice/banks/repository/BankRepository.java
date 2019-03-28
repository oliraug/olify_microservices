package com.olify.eprice.microservice.banks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.banks.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>{
	Bank findByBankName(String string);
}