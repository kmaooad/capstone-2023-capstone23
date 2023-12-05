package edu.kmaooad.capstone23.notifs.events;

public class NotifEvent {
    private String id;
    private String userId;
    private String notificationType;
    private String notificationMethod;

    public NotifEvent(
            String id,
            String userId,
            String notificationType
    ) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.notificationMethod = notificationMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}