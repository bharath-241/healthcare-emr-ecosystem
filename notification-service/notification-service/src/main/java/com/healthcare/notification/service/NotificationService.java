package com.healthcare.notification.service;

import com.healthcare.notification.dto.NotificationRequest;
import com.healthcare.notification.dto.NotificationResponse;
import com.healthcare.notification.entity.Notification;
import com.healthcare.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponse sendEmail(NotificationRequest request) {

        System.out.println(
                "MOCK EMAIL SENT TO: " + request.getRecipient()
        );

        Notification notification = new Notification(
                request.getPatientId(),
                "EMAIL",
                request.getRecipient(),
                request.getSubject(),
                request.getMessage(),
                "SENT",
                LocalDateTime.now().toString()
        );

        return convertToResponse(
                notificationRepository.save(notification)
        );
    }

    public NotificationResponse sendSms(NotificationRequest request) {

        System.out.println(
                "MOCK SMS SENT TO: " + request.getRecipient()
        );

        Notification notification = new Notification(
                request.getPatientId(),
                "SMS",
                request.getRecipient(),
                request.getSubject(),
                request.getMessage(),
                "SENT",
                LocalDateTime.now().toString()
        );

        return convertToResponse(
                notificationRepository.save(notification)
        );
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public NotificationResponse getNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found"));

        return convertToResponse(notification);
    }

    private NotificationResponse convertToResponse(
            Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getPatientId(),
                notification.getType(),
                notification.getRecipient(),
                notification.getSubject(),
                notification.getMessage(),
                notification.getStatus(),
                notification.getCreatedAt()
        );
    }
}
