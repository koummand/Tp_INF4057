package com.m1.fonda.event;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class CompteEvent {

@JsonProperty("account_id")
private String account_id;
@JsonProperty("clientId")
private int clientId;
@JsonProperty("typeBanque")
private String typeBanque;
@JsonProperty("solde")
private BigDecimal solde=new BigDecimal(0);;

public CompteEvent() {
super();
}

public CompteEvent(String account_id, int clientId, String typeBanque, BigDecimal solde) {
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
