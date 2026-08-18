package com.healthcare.doctor.controller;

import com.healthcare.doctor.dto.DoctorRequest;
import com.healthcare.doctor.dto.DoctorResponse;
import com.healthcare.doctor.entity.Leave;
import com.healthcare.doctor.entity.Schedule;
import com.healthcare.doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(
            @Valid @RequestBody DoctorRequest request) {

        return ResponseEntity.ok(doctorService.createDoctor(request));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest request) {

        return ResponseEntity.ok(doctorService.updateDoctor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @PostMapping("/{id}/schedule")
    public ResponseEntity<Schedule> addSchedule(
            @PathVariable Long id,
            @RequestBody Schedule schedule) {

        return ResponseEntity.ok(
                doctorService.addSchedule(id, schedule)
        );
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<Schedule>> getSchedules(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getSchedules(id)
        );
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Leave> addLeave(
            @PathVariable Long id,
            @RequestBody Leave leave) {

        return ResponseEntity.ok(
                doctorService.addLeave(id, leave)
        );
    }

    @GetMapping("/{id}/leave")
    public ResponseEntity<List<Leave>> getLeaves(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getLeaves(id)
        );
    }
}
