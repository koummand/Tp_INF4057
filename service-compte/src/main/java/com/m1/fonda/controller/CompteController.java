package com.m1.fonda.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1.fonda.model.Compte;
import com.m1.fonda.service.CompteService;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

	// CompteController.java

	private final CompteService compteService;

	public CompteController(CompteService compteService) {
		this.compteService = compteService;
	}

	@GetMapping("/{accountId}/solde")
	public BigDecimal getSolde(@PathVariable String accountId) {
		return compteService.getSolde(accountId);
	}

	@PutMapping("/{accountId}/solde")
	public String miseAJourSolde(@PathVariable String accountId, @PathVariable BigDecimal montant) {
		compteService.miseAJourSolde(accountId, montant);
		return "Solde mis à jour avec succès";
	}
 
	@PutMapping("/{userId}/compte")
	public Compte creerCompte(@PathVariable int userId, @PathVariable String bankType) {
		Compte compte = compteService.creerCompte(userId, bankType);
		return compte;
	}
}
