package com.healthcare.patient.controller;

import com.healthcare.patient.entity.Patient;
import com.healthcare.patient.service.PatientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // =========================
    // CREATE PATIENT
    // =========================
    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @RequestBody Patient patient) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.createPatient(patient));
    }

    // =========================
    // GET ALL PATIENTS
    // =========================
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {

        return ResponseEntity.ok(
                patientService.getAllPatients()
        );
    }

    // =========================
    // GET PATIENT BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                patientService.getPatientById(id)
        );
    }

    // =========================
    // UPDATE PATIENT
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        return ResponseEntity.ok(
                patientService.updatePatient(id, patient)
        );
    }

    // =========================
    // DELETE PATIENT
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                patientService.deletePatient(id)
        );
    }
}