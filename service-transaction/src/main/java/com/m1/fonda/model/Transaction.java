package com.m1.fonda.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	@Id
	private String transaction_id;
	private String account_id;
	private BigDecimal montant;
	private float amount;
	private String Type_transaction;

	public Transaction() {
		super();
	}
	public Transaction(String transaction_id, String account_id, BigDecimal montant,float amount, String type_transaction) {
		super();
		this.transaction_id = transaction_id;
		this.account_id = account_id;
		this.montant = montant;
		this.amount=amount;
		this.Type_transaction = type_transaction;
	}

	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount=amount;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public String getType_transaction() {
		return Type_transaction;
	}
	public void setType_transaction(String type_transaction) {
		Type_transaction = type_transaction;
	}
}
