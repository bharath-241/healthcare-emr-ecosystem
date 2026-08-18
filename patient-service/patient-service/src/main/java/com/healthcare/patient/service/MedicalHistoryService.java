package com.healthcare.patient.service;

import com.healthcare.patient.entity.MedicalHistory;
import com.healthcare.patient.repository.MedicalHistoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(
            MedicalHistoryRepository medicalHistoryRepository) {

        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    // =========================
    // CREATE MEDICAL HISTORY
    // =========================
    public MedicalHistory createMedicalHistory(
            MedicalHistory medicalHistory) {

        return medicalHistoryRepository.save(medicalHistory);
    }

    // =========================
    // GET ALL MEDICAL HISTORY
    // =========================
    public List<MedicalHistory> getAllMedicalHistory() {

        return medicalHistoryRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public MedicalHistory getMedicalHistoryById(Long id) {

        return medicalHistoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medical history not found with id: " + id
                        )
                );
    }

    // =========================
    // GET BY PATIENT ID
    // =========================
    public List<MedicalHistory> getByPatientId(Long patientId) {

        return medicalHistoryRepository.findByPatientId(patientId);
    }

    // =========================
    // UPDATE
    // =========================
    public MedicalHistory updateMedicalHistory(
            Long id,
            MedicalHistory updatedHistory) {

        MedicalHistory existingHistory =
                medicalHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medical history not found with id: " + id
                                )
                        );

        existingHistory.setPatientId(
                updatedHistory.getPatientId()
        );

        existingHistory.setConditionName(
                updatedHistory.getConditionName()
        );

        existingHistory.setDescription(
                updatedHistory.getDescription()
        );

        return medicalHistoryRepository.save(existingHistory);
    }

    // =========================
    // DELETE
    // =========================
    public String deleteMedicalHistory(Long id) {

        MedicalHistory history =
                medicalHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medical history not found with id: " + id
                                )
                        );

        medicalHistoryRepository.delete(history);

        return "Medical history deleted successfully";
    }
}