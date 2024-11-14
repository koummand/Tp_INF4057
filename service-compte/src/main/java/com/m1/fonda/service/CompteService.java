package com.m1.fonda.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1.fonda.event.NotificationEvent;
import com.m1.fonda.event.TransactionEvent;
import com.m1.fonda.event.CompteEvent;

import com.m1.fonda.model.Compte;
import com.m1.fonda.repository.CompteRepository;

@Service
public class CompteService {

@Autowired
private RabbitTemplate rabbitTemplate;

@Autowired
private CompteRepository comteRepository;

public Compte creerCompte(int userId, String bankType) {
String accountId = userId + "_" + bankType + "_account";
Compte compte = new Compte();
compte.setAccount_id(accountId);
compte.setClientId(userId);
compte.setTypeBanque(bankType);


Compte compterepos = comteRepository.save(compte);
CompteEvent event = new CompteEvent();
event.setAccount_id(compterepos.getAccount_id());
event.setClientId(compterepos.getClientId());
event.setTypeBanque(compterepos.getTypeBanque());
event.setSolde(compterepos.getSolde());

System.out.println("compte creer avec succes "+ event);

NotificationEvent notificationEvent = new NotificationEvent();
notificationEvent.setAccount_id(compterepos.getAccount_id());
notificationEvent.setClientId(compterepos.getClientId());
notificationEvent.setDate(LocalDateTime.now().toString());
notificationEvent.setStatus("VALIDÉ");
notificationEvent.setMessage("compte creer avec succes");

System.out.println("envoie de notification au service de notification "+ notificationEvent);
rabbitTemplate.convertAndSend("compteExchange", "compte.created", notificationEvent);
return compterepos;
}

public BigDecimal getSolde(String accountId) {
Compte compte = comteRepository.findById(accountId).orElseThrow();
return compte.getSolde();
}

public void miseAJourSolde(String accountId, BigDecimal montant) {
Compte compte = comteRepository.findById(accountId).orElseThrow();
if (compte != null) {
compte.miseAjourSolde(montant);
} else {
System.out.println("vous n'avez pas de compte");
}
}

public void effectuerDepot(TransactionEvent transactionEvent) {

BigDecimal montant = transactionEvent.getMontant();
String accountId = transactionEvent.getAccount_id();

this.miseAJourSolde(accountId, montant);
BigDecimal nouveauSolde = this.getSolde(accountId);

Compte compte = comteRepository.findById(accountId).orElseThrow();
int clientId = compte.getClientId();

// notifier le client
NotificationEvent notificationEvent = new NotificationEvent();
notificationEvent.setAccount_id(accountId);
notificationEvent.setClientId(clientId);
notificationEvent.setDate(LocalDateTime.now().toString());
notificationEvent.setStatus("VALIDÉ");
notificationEvent.setMessage(
"l'operation de depot de " + montant + " a reuissit votre nouveau solde est de: " + nouveauSolde);

rabbitTemplate.convertAndSend("transactionExchange", "transaction.created", notificationEvent);

}

public void effectuerRetrait(TransactionEvent transactionEvent) {

BigDecimal montant = transactionEvent.getMontant();
String accountId = transactionEvent.getAccount_id();
BigDecimal soldeActuel = this.getSolde(accountId);
NotificationEvent notificationEvent = new NotificationEvent();
Compte compte = comteRepository.findById(accountId).orElseThrow();
int clientId = compte.getClientId();

if (soldeActuel.compareTo(montant) >= 0) {

this.miseAJourSolde(accountId, montant);
BigDecimal nouveauSolde = this.getSolde(accountId);

// notifier le client
notificationEvent.setAccount_id(accountId);
notificationEvent.setClientId(clientId);
notificationEvent.setDate(LocalDateTime.now().toString());
notificationEvent.setStatus("VALIDÉ");
notificationEvent.setMessage(
"l'operation de retrait de " + montant + " a reuissit votre nouveau solde est de: " + nouveauSolde);

rabbitTemplate.convertAndSend("transactionExchange", "transaction.created", notificationEvent);

} else {
notificationEvent.setAccount_id(accountId);
notificationEvent.setClientId(clientId);
notificationEvent.setDate(LocalDateTime.now().toString());
notificationEvent.setStatus("REFUSÉ");
notificationEvent.setMessage("Solde insuffisant");

rabbitTemplate.convertAndSend("transactionExchange", "transaction.created", notificationEvent);
}
}
}
