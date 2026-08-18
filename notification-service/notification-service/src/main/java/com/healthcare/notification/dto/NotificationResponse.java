package com.healthcare.notification.dto;

public class NotificationResponse {

    private Long id;
    private Long patientId;
    private String type;
    private String recipient;
    private String subject;
    private String message;
    private String status;
    private String createdAt;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, Long patientId, String type,
                                 String recipient, String subject,
                                 String message, String status,
                                 String createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.type = type;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getType() {
        return type;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
