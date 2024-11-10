package com.m1fonda.demande.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.demande.DemandeRepository.DemandeRepository;
import com.m1fonda.demande.event.DemandeEvent;
import com.m1fonda.demande.model.Demande;

@Service
public class DemandeService {
	private DemandeRepository demandeRepos;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	// envoie de la demande par l'utilisateur
	public void envoyerDemandeCreationCompte(Demande demande) {
		try {
			DemandeEvent event = new DemandeEvent();

			event.setClientId(demande.getClientId());
			event.setNom(demande.getNom());
			event.setCni(demande.getCni());
			event.setPassword(demande.getPassword());
			event.setPhoneNumber(demande.getPhoneNumber());
			event.setTypeBanque(demande.getTypeBanque());
			
			//savegarde la demande
			demandeRepos.save(demande);
			
			// Publier la demande dans la queue "userQueue"
			rabbitTemplate.convertAndSend("demandeExchange", "demande.created", event);
			System.out.println("Demande soumise : " + event);

		} catch (Exception e) {
			throw new RuntimeException("Error lors de l'envoie de la demande", e);

		}
	}
}
