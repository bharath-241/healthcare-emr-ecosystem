package com.healthcare.patient.repository;

import com.healthcare.patient.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository
        extends JpaRepository<MedicalHistory, Long> {

    List<MedicalHistory> findByPatientId(Long patientId);
}