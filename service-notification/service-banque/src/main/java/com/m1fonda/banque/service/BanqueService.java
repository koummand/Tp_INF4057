package com.m1fonda.banque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.banque.model.Validation;
import com.m1fonda.banque.repository.BanqueRepository;
import com.m1fonda.banque.event.DemandeEvent;
//import com.m1fonda.banque.model.Demande;
import com.m1fonda.banque.model.Userbanque;

@Service
public class BanqueService {
	
	@Autowired
	private BanqueRepository banqueRepository;
	
	
	public boolean validerDemande(DemandeEvent demande) {
		//statu de la demande encoure
        // Simuler les informations de l'utilisateur (dans un cas réel, cela proviendrait d'une base de données)
		Userbanque userBanque = banqueRepository.findById(demande.getClientId()).orElseThrow();

        if (userBanque == null) {
            System.out.println("Utilisateur introuvable");
//            Notification notification = Notification();
//            notification.setMessage("Utilisateur introuvable");
            return false; // L'utilisateur n'existe pas
        }

        // Règle 1 : Vérifier si le compte est actif
        if (!userBanque.getCompteActif()) {
            System.out.println("Le compte de l'utilisateur est inactif");
//            Notification notification = Notification();
//            notification.setMessage("Le compte de l'utilisateur est inactif");
            return false;
        }

        // Validation réussie
        System.out.println("Demande validée");
//        Notification notification = Notification();
//        notification.setMessage("Demande validée");
        return true;
    }

}
