package scheduled.demo.entity.notification;

public enum Channel {

    EMAIL(1L, "email"),
    SMS(2L, "sms"),
    PUSH(3L, "push"),
    WHATSAPP(4L, "whatsapp");

    private Long id;
    private String destination;

    Channel(Long id, String description) {
        this.id = id;
        this.destination = description;
    }

    public String getDestination() {
        return destination;
    }

    public Long getId() {
        return id;
    }
}
