package com.m1fonda.banque.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Demande {

	private int id;
	private int clientId;
	private String nom;
	private String cni;
	private String phoneNumber;
	private String password;
	private LocalDateTime dateDemande;

	public Demande() {
		super();
	}

	public Demande(int id, int clientId, String nom, String cni, String phoneNumber, String password,
			LocalDateTime dateDemande) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.nom = nom;
		this.cni = cni;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.dateDemande = dateDemande;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCni() {
		return cni;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDateTime dateDemande) {
		this.dateDemande = dateDemande;
	}

}
