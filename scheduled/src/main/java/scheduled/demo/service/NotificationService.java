package scheduled.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.repository.NotificationRepository;
import scheduled.demo.request.NotificationRequest;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void createNotification(NotificationRequest request) {
        Notification notification = request.toModel();
        notificationRepository.save(notification);
    }
}
