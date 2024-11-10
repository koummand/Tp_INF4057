package com.m1fonda.banque.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.banque.event.CompteEvent;
import com.m1fonda.banque.event.DemandeEvent;
import com.m1fonda.banque.model.Compte;
import com.m1fonda.banque.model.TypeBanque;
import com.m1fonda.banque.model.Userbanque;
import com.m1fonda.banque.repository.BanqueRepository;

@Service
public class BanqueService {
	@Autowired
	private BanqueRepository banqueRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public BanqueService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public boolean validerDemande(DemandeEvent demande) {
		int clientId = demande.getClientId();
		Userbanque userBanque = banqueRepository.findById(clientId).orElse(null);

		if (userBanque == null) {
			System.out.println("Utilisateur introuvable");
			return false; // L'utilisateur n'existe pas

		}

		if (!userBanque.getCompteActif()) {
			System.out.println("Le compte de l'utilisateur est inactif");
			return false;// Le compte de l'utilisateur est inactif"
		}
		if (!(demande.getTypeBanque().toUpperCase().equals(TypeBanque.ORANGE))
				|| !(demande.getTypeBanque().toUpperCase().equals(TypeBanque.MTN))
				|| !(demande.getTypeBanque().toUpperCase().equals(TypeBanque.CAMTEL))) {
			System.out.printf("veillez choisir parmie les operateur suivant %s, %s, %s", TypeBanque.ORANGE,
					TypeBanque.MTN, TypeBanque.CAMTEL);
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

		rabbitTemplate.convertAndSend("banqueExchange", "banque.created", comptevent);// publication pour la creation
																						// d'un compte
	}

}
