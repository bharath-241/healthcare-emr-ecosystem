package com.healthcare.appointment.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentKafkaProducer {

    private static final String TOPIC = "appointment-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public AppointmentKafkaProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {

        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendAppointmentEvent(AppointmentEvent event) {

        try {
            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(
                    TOPIC,
                    String.valueOf(event.getAppointmentId()),
                    json
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Failed to convert appointment event to JSON",
                    e
            );
        }
    }
}