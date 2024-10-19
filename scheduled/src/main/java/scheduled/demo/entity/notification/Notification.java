package scheduled.demo.entity.notification;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private String destination;

    private String message;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Deprecated
    public Notification() { }

    private Notification(String destination, String message, Channel channel, Status status) {
        this.destination = destination;
        this.message = message;
        this.channel = channel;
        this.status = status;
    }

    public static Notification createNotification(String destination, String message, Channel channel) {
        return new Notification(destination, message, channel, Status.PENDING);
    }
}
