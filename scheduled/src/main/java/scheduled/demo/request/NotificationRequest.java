package scheduled.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import scheduled.demo.entity.notification.Channel;
import scheduled.demo.entity.notification.Notification;

import java.time.LocalDateTime;

public record NotificationRequest(@NotNull String destination, @NotNull String message, @NotBlank String channel, @NotNull LocalDateTime dateToProcess) {

    public Notification toModel() {
        return Notification.createNotification(destination, message, Channel.findBy(channel), dateToProcess);
    }
}