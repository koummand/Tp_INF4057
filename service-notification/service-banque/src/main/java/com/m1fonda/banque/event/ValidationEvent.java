package com.m1fonda.banque.event;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.ToString;

@ToString
public class ValidationEvent {
public ValidationEvent() {
super();
}
public ValidationEvent(int demandeId, String validationStatus, String motif, LocalDateTime dateValidation) {
super();
this.demandeId = demandeId;
this.validationStatus = validationStatus;
this.motif = motif;
this.dateValidation = dateValidation;
}
@JsonProperty("demandeId")
private int demandeId;
@JsonProperty("validationStatus")
private String validationStatus; 
@JsonProperty("motif")// "VALIDÉ" ou "REFUSÉ"
private String motif; // Optionnel, raison du refus
@JsonProperty("dateValidation")
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
