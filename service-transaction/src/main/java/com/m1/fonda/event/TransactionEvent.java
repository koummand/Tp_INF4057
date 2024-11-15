package com.m1.fonda.event;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class TransactionEvent {

	@JsonProperty("transaction_id")
	private String transaction_id;
	@JsonProperty("account_id")
	private String account_id;
	@JsonProperty("montant")
	private BigDecimal montant;
	@JsonProperty("amount")
	private float amount;
	@JsonProperty("Type_transaction")
	private String Type_transaction;

	public TransactionEvent() {
		super();
	}

	public TransactionEvent(String transaction_id, String account_id, BigDecimal montant,float amount, String type_transaction) {
		super();
		this.transaction_id = transaction_id;
		this.account_id = account_id;
		this.montant = montant;
		this.amount = amount;
		Type_transaction = type_transaction;
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

	public BigDecimal getMontant() {
		return montant;
	}
	

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public float getAmount() {
		return amount;
	}
	

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getType_transaction() {
		return Type_transaction;
	}

	public void setType_transaction(String type_transaction) {
		Type_transaction = type_transaction;
	}
}
