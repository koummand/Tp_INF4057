package com.serviceusers.userBroquer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceusers.event.UserEvent;
import com.serviceusers.event.ValidationEvent;
import com.serviceusers.model.Userbanque;
import com.serviceusers.model.Validation;
import com.serviceusers.repository.UserRepository;

//creation du consommateur pour ecouter ou consommer l'evenement
@Service
public class UserConsumer {
	@Autowired
	private UserRepository userRepository;

	// on utilise rabbit Listener utilise userQueue pour consommer l'évenenment
	@RabbitListener(queues = "userCopy")
	public void receiveUserEvent(UserEvent event) {
		try {

			Userbanque userbanque = new Userbanque();

			userbanque.setUserName(event.getUserName());
			userbanque.setPassword(event.getPassword());
			userbanque.setCni(event.getCni());
			userbanque.setEmail(event.getEmail());
			userbanque.setPhoneNumber(event.getPhoneNumber());
			userbanque.setCompteActif(event.getCompteActif());

			userRepository.save(userbanque);

		} catch (Exception e) {
			System.err.println("erreur l'or du traitement de l'évenement:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	

	@RabbitListener(queues = "validationQueue")
	public void recevoirValidation(ValidationEvent event) {

		Validation validation = new Validation();

		validation.setDemandeId(event.getDemandeId());
		validation.setValidationStatus(event.getValidationStatus());
		validation.setDateValidation(event.getDateValidation());
		validation.setMotif(event.getMotif());

		System.out.println("Message de validation reçu : " + event);

		if (validation.getValidationStatus().equals("VALIDÉ")) {
			creerCompteUtilisateur(event.getDemandeId());
			System.out.println("Compte utilisateur créé avec succès pour la demande : " + event.getDemandeId());
		} else {
			System.out.println("Validation échouée pour la demande : " + event.getDemandeId() + ", Motif : "
					+ event.getMotif());
		}
	}

	public void creerCompteUtilisateur(String demandeId) {
		// Logique de création du compte utilisateur
		Userbanque utilisateur = new Userbanque();
		// utilisateur.setId(demandeId); // Associe la demande au compte
		utilisateur.setCompteActif(true);
		// Sauvegarder dans la base de données ou autre persistance
	}

}
