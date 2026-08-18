package com.healthcare.notification.controller;

import com.healthcare.notification.dto.NotificationRequest;
import com.healthcare.notification.dto.NotificationResponse;
import com.healthcare.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<NotificationResponse> sendEmail(
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity.ok(
                notificationService.sendEmail(request)
        );
    }

    @PostMapping("/sms")
    public ResponseEntity<NotificationResponse> sendSms(
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity.ok(
                notificationService.sendSms(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>>
    getAllNotifications() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotification(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationService.getNotification(id)
        );
    }
}
