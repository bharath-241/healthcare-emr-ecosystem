package com.healthcare.emr.repository;

import com.healthcare.emr.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    List<Diagnosis> findByMedicalRecordId(Long medicalRecordId);
}
