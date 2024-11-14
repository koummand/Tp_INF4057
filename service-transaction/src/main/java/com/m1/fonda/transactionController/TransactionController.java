package com.m1.fonda.transactionController;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1.fonda.model.Transaction;
import com.m1.fonda.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	private TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

//	public String effectuerDepot(@PathVariable String accountId, @PathVariable BigDecimal montant) {
	@PostMapping("/depot")
	public String effectuerDepot(@RequestBody Transaction transaction) {
		String accountId = transaction.getAccount_id();
		float amount = transaction.getAmount();
        transaction.setMontant(new BigDecimal(amount));
		BigDecimal montant = transaction.getMontant();

		transactionService.effectuerDepot(accountId, montant);
		return "Dépôt effectué avec succès";
	}

//	public String effectuerRetrait(@PathVariable String accountId, @PathVariable BigDecimal montant) {
	@PostMapping("/retrait")
	public String effectuerRetrait(@RequestBody Transaction transaction) {

		try {
			String accountId = transaction.getAccount_id();
			float amount = transaction.getAmount();
	        transaction.setMontant(new BigDecimal(amount));
			BigDecimal montant = transaction.getMontant();
			transactionService.effectuerRetrait(accountId, montant);
			return "Retrait effectué avec succès";
		} catch (Exception e) {
			return "Erreur : " + e.getMessage();
		}
	}
}
