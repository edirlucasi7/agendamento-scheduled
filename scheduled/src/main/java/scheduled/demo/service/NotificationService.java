package scheduled.demo.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.repository.NotificationRepository;
import scheduled.demo.request.NotificationRequest;

import java.util.Optional;

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

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public void cancelNotification(Long id) {
        notificationRepository.updateStatusToCanceled(id);
    }
}
