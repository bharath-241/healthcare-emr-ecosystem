package com.healthcare.emr.service;

import com.healthcare.emr.dto.EmrRequest;
import com.healthcare.emr.dto.EmrResponse;
import com.healthcare.emr.entity.Diagnosis;
import com.healthcare.emr.entity.LabReport;
import com.healthcare.emr.entity.MedicalRecord;
import com.healthcare.emr.entity.Prescription;
import com.healthcare.emr.repository.DiagnosisRepository;
import com.healthcare.emr.repository.LabReportRepository;
import com.healthcare.emr.repository.MedicalRecordRepository;
import com.healthcare.emr.repository.PrescriptionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmrService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final LabReportRepository labReportRepository;

    public EmrService(
            MedicalRecordRepository medicalRecordRepository,
            DiagnosisRepository diagnosisRepository,
            PrescriptionRepository prescriptionRepository,
            LabReportRepository labReportRepository) {

        this.medicalRecordRepository = medicalRecordRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.labReportRepository = labReportRepository;
    }

    // =====================================================
    // CREATE MEDICAL RECORD
    // =====================================================
    public EmrResponse createMedicalRecord(EmrRequest request) {

        MedicalRecord record = new MedicalRecord(
                request.getPatientId(),
                request.getDoctorId(),
                request.getVisitDate(),
                request.getSymptoms(),
                request.getNotes()
        );

        MedicalRecord savedRecord =
                medicalRecordRepository.save(record);

        return convertToResponse(savedRecord);
    }

    // =====================================================
    // GET ALL MEDICAL RECORDS
    // =====================================================
    public List<EmrResponse> getAllMedicalRecords() {

        return medicalRecordRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =====================================================
    // GET MEDICAL RECORD BY ID
    // =====================================================
    public EmrResponse getMedicalRecord(Long id) {

        MedicalRecord record =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medical record not found"));

        return convertToResponse(record);
    }

    // =====================================================
    // GET MEDICAL RECORDS BY PATIENT
    // =====================================================
    public List<EmrResponse> getPatientRecords(Long patientId) {

        return medicalRecordRepository.findByPatientId(patientId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =====================================================
    // GET MEDICAL RECORDS BY DOCTOR
    // =====================================================
    public List<EmrResponse> getDoctorRecords(Long doctorId) {

        return medicalRecordRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // =====================================================
    // ADD DIAGNOSIS
    // =====================================================
    public Diagnosis addDiagnosis(
            Long recordId,
            Diagnosis diagnosis) {

        if (!medicalRecordRepository.existsById(recordId)) {
            throw new RuntimeException(
                    "Medical record not found");
        }

        diagnosis.setMedicalRecordId(recordId);

        return diagnosisRepository.save(diagnosis);
    }

    // =====================================================
    // GET DIAGNOSES
    // =====================================================
    public List<Diagnosis> getDiagnoses(Long recordId) {

        return diagnosisRepository
                .findByMedicalRecordId(recordId);
    }

    // =====================================================
    // ADD PRESCRIPTION
    // =====================================================
    public Prescription addPrescription(
            Long recordId,
            Prescription prescription) {

        if (!medicalRecordRepository.existsById(recordId)) {
            throw new RuntimeException(
                    "Medical record not found");
        }

        prescription.setMedicalRecordId(recordId);

        return prescriptionRepository.save(prescription);
    }

    // =====================================================
    // GET PRESCRIPTIONS
    // =====================================================
    public List<Prescription> getPrescriptions(Long recordId) {

        return prescriptionRepository
                .findByMedicalRecordId(recordId);
    }

    // =====================================================
    // ADD LAB REPORT
    // =====================================================
    public LabReport addLabReport(
            Long recordId,
            LabReport labReport) {

        if (!medicalRecordRepository.existsById(recordId)) {
            throw new RuntimeException(
                    "Medical record not found");
        }

        labReport.setMedicalRecordId(recordId);

        return labReportRepository.save(labReport);
    }

    // =====================================================
    // GET LAB REPORTS
    // =====================================================
    public List<LabReport> getLabReports(Long recordId) {

        return labReportRepository
                .findByMedicalRecordId(recordId);
    }

    // =====================================================
    // CONVERT ENTITY TO RESPONSE
    // =====================================================
    private EmrResponse convertToResponse(
            MedicalRecord record) {

        return new EmrResponse(
                record.getId(),
                record.getPatientId(),
                record.getDoctorId(),
                record.getVisitDate(),
                record.getSymptoms(),
                record.getNotes()
        );
    }
}