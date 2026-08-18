package com.healthcare.appointment.controller;

import com.healthcare.appointment.dto.AppointmentRequest;
import com.healthcare.appointment.dto.AppointmentResponse;
import com.healthcare.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // =====================================================
    // OPENFEIGN TEST
    // =====================================================

    @GetMapping("/test-doctor/{id}")
    public ResponseEntity<Object> testDoctor(@PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.getDoctorFromDoctorService(id)
        );
    }

    // =====================================================
    // CREATE APPOINTMENT
    // =====================================================

    @PostMapping
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @Valid @RequestBody AppointmentRequest request) {

        return ResponseEntity.ok(
                appointmentService.bookAppointment(request)
        );
    }

    // =====================================================
    // GET ALL APPOINTMENTS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {

        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    // =====================================================
    // GET APPOINTMENT BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id)
        );
    }

    // =====================================================
    // GET PATIENT APPOINTMENTS
    // =====================================================

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getPatientAppointments(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                appointmentService.getPatientAppointments(patientId)
        );
    }

    // =====================================================
    // GET DOCTOR APPOINTMENTS
    // =====================================================

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointments(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                appointmentService.getDoctorAppointments(doctorId)
        );
    }

    // =====================================================
    // CANCEL APPOINTMENT
    // =====================================================

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.cancelAppointment(id)
        );
    }

    // =====================================================
    // RESCHEDULE APPOINTMENT
    // =====================================================

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<AppointmentResponse> rescheduleAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequest request) {

        return ResponseEntity.ok(
                appointmentService.rescheduleAppointment(id, request)
        );
    }
}