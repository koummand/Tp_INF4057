package com.m1.fonda.service;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1.fonda.event.TransactionEvent;
import com.m1.fonda.model.Transaction;
import com.m1.fonda.transactionRepository.TransactionRepository;

@Service
public class TransactionService {

//	@Autowired
//	private final RestTemplate restTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction effectuerDepot(String accountId, BigDecimal montant) {

		// Crée et enregistre la transaction
		Transaction transaction = new Transaction();

		transaction.setTransaction_id("depot_" + accountId);
		transaction.setAccount_id(accountId);
		transaction.setMontant(montant);
		transaction.setType_transaction("DEPOSIT");

		TransactionEvent transactionEvent = new TransactionEvent();

		transactionEvent.setTransaction_id(transaction.getTransaction_id());
		transactionEvent.setAccount_id(transaction.getAccount_id());
		transactionEvent.setMontant(transaction.getMontant());
		transactionEvent.setType_transaction(transaction.getType_transaction());
	
		rabbitTemplate.convertAndSend("transactionExchange", "transaction.created", transactionEvent);
		Transaction transactionrepos = transactionRepository.save(transaction);

		return transactionrepos;
	}

	public Transaction effectuerRetrait(String accountId, BigDecimal montant) throws Exception {

		Transaction transaction = new Transaction();
		transaction.setTransaction_id("retrait_" + accountId);
		transaction.setAccount_id(accountId);
		transaction.setMontant(montant.negate());
		transaction.setType_transaction("WITHDRAWAL");

		TransactionEvent transactionEvent = new TransactionEvent();
		transactionEvent.setTransaction_id(transaction.getTransaction_id());
		transactionEvent.setAccount_id(transaction.getAccount_id());
		transactionEvent.setMontant(transaction.getMontant());
		transactionEvent.setType_transaction(transaction.getType_transaction());

		rabbitTemplate.convertAndSend("transactionExchange", "transaction.created", transactionEvent);
		Transaction transactionrepos = transactionRepository.save(transaction);
		return transactionrepos;

	}
}
