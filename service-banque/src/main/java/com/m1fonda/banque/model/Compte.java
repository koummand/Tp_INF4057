package com.m1fonda.banque.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Compte {

	@Id
	private String account_id;
	private int clientId;
	private String typeBanque;
	private BigDecimal solde;

	public Compte() {
		super();
	}

	public Compte(String account_id, int clientId, String typeBanque, BigDecimal solde) {
		super();
		this.account_id = account_id;
		this.clientId = clientId;
		this.typeBanque = typeBanque;
		this.solde = solde;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getTypeBanque() {
		return typeBanque;
	}

	public void setTypeBanque(String typeBanque) {
		this.typeBanque = typeBanque;
	}

	public BigDecimal getSolde() {
		return solde;
	}

	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

	public void miseAjourSolde(BigDecimal montant) {
		this.solde = this.solde.add(montant);
	}

}
