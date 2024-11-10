package com.serviceusers.userBroquer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.serviceusers.event.NotificationEvent;

//creation du consommateur pour ecouter ou consommer l'evenement
@Service
public class UserConsumer {
//	@Autowired
//	private UserRepository userRepository;

	@RabbitListener(queues = "notificationQueue")
	public void recevoirNotification(NotificationEvent event) {

		NotificationEvent notification = new NotificationEvent();

		notification.setAccount_id(event.getAccount_id());
		notification.setClientId(event.getClientId());
		notification.setMessage(event.getMessage());
		notification.setDate(event.getDate());
		notification.setStatus(event.getStatus());

		System.out.println("Message de validation reçu : " + event);

		if (notification.getStatus().equals("VALIDÉ")) {
			System.out.println(notification.getMessage() + " " + event.getClientId());
		} else {
			System.out.println(notification.getMessage() + " " + event.getClientId());
		}
	}

}
