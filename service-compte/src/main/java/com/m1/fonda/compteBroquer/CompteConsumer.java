package com.m1.fonda.compteBroquer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1.fonda.event.CompteEvent;
import com.m1.fonda.event.TransactionEvent;
import com.m1.fonda.model.Compte;
import com.m1.fonda.service.CompteService;

@Service
public class CompteConsumer {

@Autowired
private CompteService compteService;

@RabbitListener(queues = "banqueQueue")
public void creerCompte(CompteEvent event) {

System.out.println("requete recu pour creation de  compte ");

compteService.creerCompte(event.getClientId(), event.getTypeBanque());

}

@RabbitListener(queues = "transactionQueue")
public void effectuerDepot(TransactionEvent event) {

compteService.effectuerDepot(event);

}

@RabbitListener(queues = "transactionQueue")
public void effectuerRetrait(TransactionEvent event) {

compteService.effectuerRetrait(event);

}

}
