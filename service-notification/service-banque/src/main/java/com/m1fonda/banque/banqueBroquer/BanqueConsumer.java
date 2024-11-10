package com.m1fonda.banque.banqueBroquer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.banque.event.DemandeEvent;
import com.m1fonda.banque.event.ValidationEvent;
//import com.m1fonda.banque.model.Demande;
import com.m1fonda.banque.model.Validation;
import com.m1fonda.banque.service.BanqueService;
import com.m1fonda.banque.model.Userbanque;

@Service
public class BanqueConsumer {
@Autowired
private RabbitTemplate rabbitTemplate;

@Autowired
private BanqueService banqueService;

@RabbitListener(queues = "transmissionQueue")
public void validerDemande(DemandeEvent event) {


System.out.println("Demande reçue pour validation : " + event);

// Logique de validation
boolean isValid = banqueService.validerDemande(event);

Validation validation = new Validation();
//		Notification notification = new Notification();
ValidationEvent eventvalid = new ValidationEvent();
//		NotificationEvent notificationEvent=new NotificationEvent();

validation.setDemandeId(event.getId());
validation.setDateValidation(LocalDateTime.now());

if (isValid) {
validation.setValidationStatus("VALIDÉ");

eventvalid.setDemandeId(validation.getDemandeId());
eventvalid.setDateValidation(validation.getDateValidation());
eventvalid.setValidationStatus(validation.getValidationStatus());

//statu de la demande accepter
rabbitTemplate.convertAndSend("notificationExchange", "notification.created", eventvalid);
//appel de notification ici
System.out.println("Demande validée et notification envoyée à l'utilisateur.");
} else {

validation.setValidationStatus("REFUSÉ");
// exemple de motif raison de l'echec
validation.setMotif(validation.getMotif());

eventvalid.setValidationStatus(validation.getValidationStatus());
eventvalid.setMotif(validation.getMotif());

//statu de la demande refuser
// envoi du resultat de validation en direction de l'utilisateur
rabbitTemplate.convertAndSend("notificationExchange","notification.created", eventvalid);
//appel de notification ici 
System.out.println("Demande refusée, et notification envoyée à l'utilisateur.");
}
}

}
