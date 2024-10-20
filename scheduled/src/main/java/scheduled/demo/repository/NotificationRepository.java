package scheduled.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.entity.notification.Status;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Query(value = "UPDATE notification SET updated_at = NOW(), status = :status WHERE id = :id ", nativeQuery = true)
    void updateStatus(Long id, String status);

    @Transactional
    default void updateStatusToCanceled(Long id) {
        updateStatus(id, Status.CANCELLED.name());
    }
}