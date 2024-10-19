package scheduled.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scheduled.demo.request.NotificationRequest;
import scheduled.demo.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    public final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<?> scheduled(@RequestBody NotificationRequest request) {
        notificationService.createNotification(request);
        return ResponseEntity.accepted().build();
    }
}
