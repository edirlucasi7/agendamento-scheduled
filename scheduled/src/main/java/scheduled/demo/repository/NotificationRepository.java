package scheduled.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.entity.notification.Status;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Query(value = "UPDATE notification SET updated_at = NOW(), status = :status WHERE id = :id ", nativeQuery = true)
    void updateStatusWithoutScheduled(Long id, String status);

    @Modifying
    @Query(value = "UPDATE notification SET executed_at = NOW(), status = :status WHERE id IN (:ids)", nativeQuery = true)
    void updateStatusWithScheduled(List<Long> ids, String status);

    @Transactional
    default void updateStatusToCanceled(Long id) {
        updateStatusWithoutScheduled(id, Status.CANCELLED.name());
    }

    @Transactional
    default void updateStatusToSuccess(List<Long> ids) {
        updateStatusWithScheduled(ids, Status.SUCCESS.name());
    }

    @Transactional
    default void updateStatusToError(List<Long> ids) {
        updateStatusWithScheduled(ids, Status.ERROR.name());
    }

    @Query(value = """
        SELECT id FROM notification WHERE status = :status LIMIT 10
    """, nativeQuery = true)
    List<Long> findByStatus(String status);

    default List<Long> findByPendingStatus() {
        return findByStatus(Status.PENDING.name());
    }
}