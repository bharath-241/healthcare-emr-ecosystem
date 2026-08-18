package com.healthcare.emr.controller;

import com.healthcare.emr.dto.EmrRequest;
import com.healthcare.emr.dto.EmrResponse;
import com.healthcare.emr.entity.Diagnosis;
import com.healthcare.emr.entity.LabReport;
import com.healthcare.emr.entity.Prescription;
import com.healthcare.emr.service.EmrService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emr")
public class EmrController {

    private final EmrService emrService;

    public EmrController(EmrService emrService) {
        this.emrService = emrService;
    }

    // =====================================================
    // CREATE MEDICAL RECORD
    // POST /api/emr
    // =====================================================
    @PostMapping
    public ResponseEntity<EmrResponse> createMedicalRecord(
            @Valid @RequestBody EmrRequest request) {

        return ResponseEntity.ok(
                emrService.createMedicalRecord(request)
        );
    }

    // =====================================================
    // GET ALL MEDICAL RECORDS
    // GET /api/emr
    // =====================================================
    @GetMapping
    public ResponseEntity<List<EmrResponse>> getAllMedicalRecords() {

        return ResponseEntity.ok(
                emrService.getAllMedicalRecords()
        );
    }

    // =====================================================
    // GET MEDICAL RECORD BY ID
    // GET /api/emr/{id}
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<EmrResponse> getMedicalRecord(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emrService.getMedicalRecord(id)
        );
    }

    // =====================================================
    // GET MEDICAL RECORDS BY PATIENT
    // GET /api/emr/patient/{patientId}
    // =====================================================
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<EmrResponse>> getPatientRecords(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                emrService.getPatientRecords(patientId)
        );
    }

    // =====================================================
    // GET MEDICAL RECORDS BY DOCTOR
    // GET /api/emr/doctor/{doctorId}
    // =====================================================
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<EmrResponse>> getDoctorRecords(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                emrService.getDoctorRecords(doctorId)
        );
    }

    // =====================================================
    // ADD DIAGNOSIS
    // POST /api/emr/{id}/diagnosis
    // =====================================================
    @PostMapping("/{id}/diagnosis")
    public ResponseEntity<Diagnosis> addDiagnosis(
            @PathVariable Long id,
            @RequestBody Diagnosis diagnosis) {

        return ResponseEntity.ok(
                emrService.addDiagnosis(id, diagnosis)
        );
    }

    // =====================================================
    // GET DIAGNOSES
    // GET /api/emr/{id}/diagnosis
    // =====================================================
    @GetMapping("/{id}/diagnosis")
    public ResponseEntity<List<Diagnosis>> getDiagnoses(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emrService.getDiagnoses(id)
        );
    }

    // =====================================================
    // ADD PRESCRIPTION
    // POST /api/emr/{id}/prescription
    // =====================================================
    @PostMapping("/{id}/prescription")
    public ResponseEntity<Prescription> addPrescription(
            @PathVariable Long id,
            @RequestBody Prescription prescription) {

        return ResponseEntity.ok(
                emrService.addPrescription(id, prescription)
        );
    }

    // =====================================================
    // GET PRESCRIPTIONS
    // GET /api/emr/{id}/prescription
    // =====================================================
    @GetMapping("/{id}/prescription")
    public ResponseEntity<List<Prescription>> getPrescriptions(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emrService.getPrescriptions(id)
        );
    }

    // =====================================================
    // ADD LAB REPORT
    // POST /api/emr/{id}/lab-report
    // =====================================================
    @PostMapping("/{id}/lab-report")
    public ResponseEntity<LabReport> addLabReport(
            @PathVariable Long id,
            @RequestBody LabReport labReport) {

        return ResponseEntity.ok(
                emrService.addLabReport(id, labReport)
        );
    }

    // =====================================================
    // GET LAB REPORTS
    // GET /api/emr/{id}/lab-report
    // =====================================================
    @GetMapping("/{id}/lab-report")
    public ResponseEntity<List<LabReport>> getLabReports(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emrService.getLabReports(id)
        );
    }
}