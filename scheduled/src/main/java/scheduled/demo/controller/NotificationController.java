package scheduled.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import scheduled.demo.entity.notification.Notification;
import scheduled.demo.request.NotificationRequest;
import scheduled.demo.service.NotificationService;

import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    public final NotificationService notificationService;
    public final NotificationChannelValidator notificationChannelValidator;

    public NotificationController(NotificationService notificationService, NotificationChannelValidator notificationChannelValidator) {
        this.notificationService = notificationService;
        this.notificationChannelValidator = notificationChannelValidator;
    }

    @InitBinder(value = "notificationRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(notificationChannelValidator);
    }

    @PostMapping
    public ResponseEntity<?> scheduled(@Valid @RequestBody NotificationRequest request) {
        notificationService.createNotification(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Optional<Notification> possibleNotification = notificationService.findById(id);
        if (possibleNotification.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(possibleNotification.get());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        notificationService.cancelNotification(id);
        return ResponseEntity.noContent().build();
    }
}