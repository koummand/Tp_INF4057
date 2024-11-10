package com.m1.fonda.service;

import java.time.LocalDate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1.fonda.NotificationRepository.NotificationRepository;
import com.m1.fonda.event.NotificationEvent;
import com.m1.fonda.model.Notification;

@Service
public class NotificationService {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private NotificationRepository notifRepos;

	public Notification receiveNotificationCompte(NotificationEvent event) {

		Notification notification = new Notification();

		notification.setAccount_id(event.getAccount_id());
		notification.setClientId(event.getClientId());
		notification.setMessage(event.getMessage());
		notification.setDate(LocalDate.now().toString());
		notification.setStatus(event.getStatus());

		// publie la notification vers le client
		rabbitTemplate.convertAndSend("notificationExchange", "notification.created", event);
		Notification notif = notifRepos.save(notification);
		return notif;

	}
}
