package scheduled.demo.request;

import scheduled.demo.entity.notification.Channel;
import scheduled.demo.entity.notification.Notification;

import javax.validation.constraints.NotNull;

public record NotificationRequest(@NotNull String destination, @NotNull String message, @NotNull Channel channel) {

    public Notification toModel() {
        return Notification.createNotification(destination, message, channel);
    }
}