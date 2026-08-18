package com.healthcare.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentEventConsumer {

    @KafkaListener(
            topics = "appointment-events",
            groupId = "notification-group"
    )
    public void consumeAppointmentEvent(String message) {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "Appointment Event Received:"
        );

        System.out.println(message);

        System.out.println(
                "========================================"
        );
    }
}