package com.m1fonda.demande.demanBroquer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1fonda.demande.event.UserEvent;
import com.m1fonda.demande.model.Userbanque;
import com.m1fonda.demande.repository.DemandeRepository;

@Service
public class DemandeConsumer {


	 @Autowired
	 private DemandeRepository demandeRepository;

	
	@RabbitListener(queues = "userQueue")
	public void receiveUserEvent(UserEvent event) {
		try {

			Userbanque userbanque = new Userbanque();

			userbanque.setUserName(event.getUserName());
			userbanque.setPassword(event.getPassword());
			userbanque.setCni(event.getCni());
			userbanque.setEmail(event.getEmail());
			userbanque.setPhoneNumber(event.getPhoneNumber());

			demandeRepository.save(userbanque);

		} catch (Exception e) {
			System.err.println("erreur l'or du traitement de l'évenement:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}


}
