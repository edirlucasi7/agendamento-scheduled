package scheduled.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.repository.NotificationRepository;
import scheduled.demo.request.NotificationRequest;

import java.util.List;
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

    @Transactional
    public void executedNotification() {
        // pode adicionar lock pessimista
        List<Long> byPendingStatus = notificationRepository.findByPendingStatus();
        if (byPendingStatus.isEmpty()) return;

        try {
            notificationRepository.updateStatusToSuccess(byPendingStatus);
            // envio da notificacao
        } catch (Exception e) {
            notificationRepository.updateStatusToError(byPendingStatus);
            // envia erro para o sentry | faz retry, etc..
        }
    }
}