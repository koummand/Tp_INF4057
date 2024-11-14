package com.m1.fonda.notificationBroquer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.m1.fonda.event.NotificationEvent;
import com.m1.fonda.service.NotificationService;

@Service
public class NotificationConsumer {

private NotificationService notificationService;

public NotificationConsumer(NotificationService notificationService) {
this.notificationService = notificationService;
}

@RabbitListener(queues = "compteQueue")
public void receiveNotificationCompte(NotificationEvent event) {
try {

notificationService.receiveNotificationCompte(event);

} catch (Exception e) {
System.err.println("erreur l'or du traitement de l'évenement: compte" + e.getMessage());
throw new RuntimeException(e);
}
}

@RabbitListener(queues = "banqueQueue")
public void receiveNotificationBanque(NotificationEvent event) {
try {

notificationService.receiveNotificationCompte(event);

} catch (Exception e) {
System.err.println("erreur l'or du traitement de l'évenement: banque" + e.getMessage());
throw new RuntimeException(e);
}
}

@RabbitListener(queues = "transactionQueue")
public void receiveNotificationTransaction(NotificationEvent event) {
try {

notificationService.receiveNotificationCompte(event);

} catch (Exception e) {
System.err.println("erreur l'or du traitement de l'évenement: transaction" + e.getMessage());
throw new RuntimeException(e);
}
}

}
