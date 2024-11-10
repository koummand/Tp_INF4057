package com.m1fonda.banque.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class BanqueEvent {

	@JsonProperty("numBanque")
	private String numBanque;
	@JsonProperty("typeBanque")
	private String typeBanque;

	public BanqueEvent() {
		super();
	}

	public BanqueEvent(String numBanque, String typeBanque) {
		super();

		this.numBanque = numBanque;
		this.typeBanque = typeBanque;
	}

	public String getNumBanque() {
		return numBanque;
	}

	public void setNumBanque(String numBanque) {
		this.numBanque = numBanque;
	}

	public String getTypeBanque() {
		return typeBanque;
	}

	public void setTypeBanque(String typeBanque) {
		this.typeBanque = typeBanque;
	}

}
