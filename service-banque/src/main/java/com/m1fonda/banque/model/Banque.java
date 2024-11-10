package com.m1fonda.banque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banque {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int banqueId;
	private String numBanque;
	private String typeBanque; // "VALIDÉ" ou "REFUSÉ"

	public Banque() {
		super();
	}

	public Banque(int banqueId, String numBanque, String typeBanque) {
		super();
		this.banqueId = banqueId;
		this.numBanque = numBanque;
		this.typeBanque = typeBanque;
	}

	public int getBanqueId() {
		return banqueId;
	}

	public void setBanqueId(int banqueId) {
		this.banqueId = banqueId;
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
		if ((typeBanque.toUpperCase().equals(TypeBanque.ORANGE))
				|| (typeBanque.toUpperCase().equals(TypeBanque.MTN))
				|| (typeBanque.toUpperCase().equals(TypeBanque.CAMTEL))) {
			this.typeBanque = typeBanque;
		}else {
			System.out.printf("veillez choisir parmie les operateur suivant %s, %s, %s", TypeBanque.ORANGE, TypeBanque.MTN,
					TypeBanque.CAMTEL);
		}
	}

	

}
