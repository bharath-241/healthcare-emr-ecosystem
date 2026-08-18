package com.healthcare.patient.controller;

import com.healthcare.patient.entity.MedicalHistory;
import com.healthcare.patient.service.MedicalHistoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    public MedicalHistoryController(MedicalHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(
            @RequestBody MedicalHistory medicalHistory) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createMedicalHistory(medicalHistory));
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistory() {

        return ResponseEntity.ok(
                service.getAllMedicalHistory()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getMedicalHistoryById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalHistory>> getByPatientId(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                service.getByPatientId(patientId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(
            @PathVariable Long id,
            @RequestBody MedicalHistory updatedHistory) {

        return ResponseEntity.ok(
                service.updateMedicalHistory(id, updatedHistory)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalHistory(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.deleteMedicalHistory(id)
        );
    }
}