package com.m1fonda.banque.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Validation {

	public Validation() {
		super();
	}

	public Validation(int demandeId, String validationStatus, String motif, LocalDateTime dateValidation) {
		super();
		this.demandeId = demandeId;
		this.validationStatus = validationStatus;
		this.motif = motif;
		this.dateValidation = dateValidation;
	}

	private int demandeId;
	private String validationStatus; // "VALIDÉ" ou "REFUSÉ"
	private String motif; // Optionnel, raison du refus
	private LocalDateTime dateValidation;

	public int getDemandeId() {
		return demandeId;
	}

	public void setDemandeId(int demandeId) {
		this.demandeId = demandeId;
	}

	public String getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public LocalDateTime getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(LocalDateTime dateValidation) {
		this.dateValidation = dateValidation;
	}

}
