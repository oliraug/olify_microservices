package com.olify.eprice.microservice.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olify.eprice.microservice.invoice.model.OlifyInvoice;

@Repository
public interface InvoiceRepository extends JpaRepository<OlifyInvoice, Long>{

}
