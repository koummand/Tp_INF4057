package com.serviceusers.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceusers.event.UserEvent;
import com.serviceusers.model.Userbanque;
import com.serviceusers.repository.UserRepository;

@Service
public class UserService {

	// Qunad tu enregistre publie l'evenement
	@Autowired
	UserRepository userRepo;

	// on utlise RabbitTemplate pour publier l'evenenment
	@Autowired
	private RabbitTemplate rabbitTemplate;

	// Qunad tu enregistre un utilisateur publie l'evenement
	public void addUsers(Userbanque user) {

		try {
			// Enregistrer un user
			Userbanque saveUser = userRepo.save(user);

			// Publier les evenemt
			UserEvent event = new UserEvent();

			event.setUserName(saveUser.getUserName());
			event.setPassword(saveUser.getPassword());
			event.setCni(saveUser.getCni());
			event.setEmail(saveUser.getEmail());
			event.setPhoneNumber(saveUser.getPhoneNumber());
			event.setCompteActif(true);

			// comme liste d'attente on a topic et queue on utilise une clé pour savoir
			// laquelle utiliser
			// "userExchange" ici represente le nom de notre liste d'attente "user.created"
			// represente la clé
			// event represente l'evenement
			rabbitTemplate.convertAndSend("userExchange", "user.created", event);

			// apres ajout de user copieh
			// ici user-service enregistre et user-service copie publie l'evenement
			// rabbitTemplate.convertAndSend("userCopyExchange","userCopy.created",event);

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error lors de l'insertion de l'utilisateur", e);
		}
		userRepo.save(user);
	}



	// suprimer un utilisateur
	public List<Userbanque> deleteUsers(int id) {
		userRepo.deleteById(id);
		return userRepo.findAll();
	}

	// recupere les utilisateur
	public List<Userbanque> getAllUsers() {
		return userRepo.findAll();
	}

	// recherche d'un utilisateur
	public Userbanque getUsers(int id) {
		return userRepo.findById(id).orElse(null);

	}
}