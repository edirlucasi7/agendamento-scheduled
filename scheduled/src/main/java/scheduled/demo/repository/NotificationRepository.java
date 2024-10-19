package scheduled.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduled.demo.entity.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}