package scheduled.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scheduled.demo.service.NotificationService;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class TaskSchedulerAction {

    private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerAction.class);

    private final NotificationService notificationService;

    public TaskSchedulerAction(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void executeNotification() {
        notificationService.executedNotification();
        logger.info("Running task scheduler {}", LocalDateTime.now());
    }
}
