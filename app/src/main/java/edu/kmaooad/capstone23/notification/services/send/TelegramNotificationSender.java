package edu.kmaooad.capstone23.notification.services.send;

import edu.kmaooad.capstone23.users.dal.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelegramNotificationSender implements NotificationSender{
    @Override
    public void sendForUser(String eventType, String payload, User user) {
        // TG send
    }
}
