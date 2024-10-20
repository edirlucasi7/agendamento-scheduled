package scheduled.demo.entity.notification;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private LocalDateTime executedAt;

    private String destination;

    private String message;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Deprecated
    public Notification() { }

    public Notification(String destination, String message, Channel channel, Status status, LocalDateTime executedAt) {
        this.destination = destination;
        this.message = message;
        this.channel = channel;
        this.status = status;
        this.executedAt = executedAt;
    }

    public static Notification createNotification(String destination, String message, Channel channel, LocalDateTime executedAt) {
        return new Notification(destination, message, channel, Status.PENDING, executedAt);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public String getDestination() {
        return destination;
    }

    public String getMessage() {
        return message;
    }

    public Channel getChannel() {
        return channel;
    }

    public Status getStatus() {
        return status;
    }
}
