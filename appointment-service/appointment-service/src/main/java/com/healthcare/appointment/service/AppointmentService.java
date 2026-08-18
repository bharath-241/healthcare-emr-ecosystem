package com.healthcare.appointment.service;

import com.healthcare.appointment.client.DoctorClient;
import com.healthcare.appointment.dto.AppointmentRequest;
import com.healthcare.appointment.dto.AppointmentResponse;
import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.kafka.AppointmentEvent;
import com.healthcare.appointment.kafka.AppointmentKafkaProducer;
import com.healthcare.appointment.repository.AppointmentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final AppointmentKafkaProducer appointmentKafkaProducer;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            DoctorClient doctorClient,
            AppointmentKafkaProducer appointmentKafkaProducer) {

        this.appointmentRepository = appointmentRepository;
        this.doctorClient = doctorClient;
        this.appointmentKafkaProducer = appointmentKafkaProducer;
    }

    // =========================================================
    // BOOK APPOINTMENT
    // =========================================================

    public AppointmentResponse bookAppointment(
            AppointmentRequest request) {

        boolean alreadyBooked =
                appointmentRepository
                        .existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatus(
                                request.getDoctorId(),
                                request.getAppointmentDate(),
                                request.getAppointmentTime(),
                                "BOOKED"
                        );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "Doctor slot is already booked"
            );
        }

        Appointment appointment = new Appointment(
                request.getPatientId(),
                request.getDoctorId(),
                request.getAppointmentDate(),
                request.getAppointmentTime(),
                "BOOKED",
                request.getReason()
        );

        // Save appointment
        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        // =====================================================
        // KAFKA EVENT
        // =====================================================

        AppointmentEvent event = new AppointmentEvent(
                savedAppointment.getId(),
                savedAppointment.getPatientId(),
                savedAppointment.getDoctorId(),
                savedAppointment.getAppointmentDate(),
                savedAppointment.getAppointmentTime(),
                savedAppointment.getStatus(),
                savedAppointment.getReason()
        );

        appointmentKafkaProducer.sendAppointmentEvent(event);

        return convertToResponse(savedAppointment);
    }

    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<AppointmentResponse> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =========================================================
    // GET APPOINTMENT BY ID
    // =========================================================

    public AppointmentResponse getAppointmentById(Long id) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Appointment not found"
                                )
                        );

        return convertToResponse(appointment);
    }

    // =========================================================
    // GET PATIENT APPOINTMENTS
    // =========================================================

    public List<AppointmentResponse> getPatientAppointments(
            Long patientId) {

        return appointmentRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =========================================================
    // GET DOCTOR APPOINTMENTS
    // =========================================================

    public List<AppointmentResponse> getDoctorAppointments(
            Long doctorId) {

        return appointmentRepository
                .findByDoctorId(doctorId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =========================================================
    // CANCEL APPOINTMENT
    // =========================================================

    public AppointmentResponse cancelAppointment(Long id) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Appointment not found"
                                )
                        );

        appointment.setStatus("CANCELLED");

        return convertToResponse(
                appointmentRepository.save(appointment)
        );
    }

    // =========================================================
    // RESCHEDULE APPOINTMENT
    // =========================================================

    public AppointmentResponse rescheduleAppointment(
            Long id,
            AppointmentRequest request) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Appointment not found"
                                )
                        );

        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        appointment.setStatus("BOOKED");

        appointment.setReason(
                request.getReason()
        );

        return convertToResponse(
                appointmentRepository.save(appointment)
        );
    }

    // =========================================================
    // OPENFEIGN + RESILIENCE4J
    // =========================================================

    @CircuitBreaker(
            name = "doctorService",
            fallbackMethod = "doctorServiceFallback"
    )
    @Retry(
            name = "doctorService"
    )
    public Object getDoctorFromDoctorService(Long doctorId) {

        System.out.println(
                "Calling Doctor Service for doctor ID: "
                        + doctorId
        );

        return doctorClient.getDoctorById(doctorId);
    }

    // =========================================================
    // CIRCUIT BREAKER FALLBACK
    // =========================================================

    public Object doctorServiceFallback(
            Long doctorId,
            Throwable throwable) {

        System.out.println(
                "Doctor Service is unavailable."
        );

        System.out.println(
                "Fallback executed for doctor ID: "
                        + doctorId
        );

        return "Doctor Service is currently unavailable. "
                + "Please try again later.";
    }

    // =========================================================
    // CONVERT ENTITY TO RESPONSE
    // =========================================================

    private AppointmentResponse convertToResponse(
            Appointment appointment) {

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                appointment.getReason()
        );
    }
}