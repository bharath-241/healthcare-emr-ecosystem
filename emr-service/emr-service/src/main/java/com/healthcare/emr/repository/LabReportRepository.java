package com.healthcare.emr.repository;

import com.healthcare.emr.entity.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabReportRepository extends JpaRepository<LabReport, Long> {

    List<LabReport> findByMedicalRecordId(Long medicalRecordId);
}
