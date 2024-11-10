package com.m1fonda.banque.model;

public enum TypeBanque {
	ORANGE(80000000), MTN(80000000), CAMTEL(80000000);

	private float capitalBase;

	TypeBanque(float capitalBase) {
		this.capitalBase = capitalBase;
	}

}
