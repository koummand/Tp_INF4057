package com.m1.fonda.transactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.m1.fonda.model.Transaction;

@RestResource
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
