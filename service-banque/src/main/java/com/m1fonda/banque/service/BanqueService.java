package com.m1fonda.banque.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.banque.event.CompteEvent;
import com.m1fonda.banque.event.DemandeEvent;
import com.m1fonda.banque.model.Compte;
import com.m1fonda.banque.model.Banque;
import com.m1fonda.banque.model.Userbanque;
import com.m1fonda.banque.repository.BanqueRepository;

@Service
public class BanqueService {


private BanqueRepository banqueRepository;

private RabbitTemplate rabbitTemplate;

public BanqueService(RabbitTemplate rabbitTemplate, BanqueRepository banqueRepository) {
this.rabbitTemplate = rabbitTemplate;
this.banqueRepository=banqueRepository;
}

public boolean validerDemande(DemandeEvent demande) {

Banque banque = new Banque();
banque.setClientId(demande.getClientId());
banque.setTypeBanque(demande.getTypeBanque());
Banque banquerepos= banqueRepository.save(banque);
int clientId = banquerepos.getClientId();

if (clientId == 0) {
System.out.println("Utilisateur introuvable");
return false; // L'utilisateur n'existe pas
}


String typeBanque = banquerepos.getTypeBanque();
if (!(typeBanque.equalsIgnoreCase("ORANGE") || 
typeBanque.equalsIgnoreCase("MTN") || 
typeBanque.equalsIgnoreCase("CAMTEL"))) {

System.out.println("Veuillez choisir parmi les opérateurs suivants : ORANGE, MTN, CAMTEL");
return false;
}


System.out.println("Demande validée");
return true;// Validation réussie
}

public void creerCompte(int userId, String bankType) {

// creer et enregistrez le compte
Compte compte = new Compte();
compte.setClientId(userId);
compte.setTypeBanque(bankType);

CompteEvent comptevent = new CompteEvent();
comptevent.setClientId(compte.getClientId());
comptevent.setTypeBanque(compte.getTypeBanque());
System.out.println("Envoi d'une requette pour la creation de compte : "+ comptevent);

rabbitTemplate.convertAndSend("banqueExchange", "banque.created", comptevent);// publication pour la creation
// d un compte
}

}
