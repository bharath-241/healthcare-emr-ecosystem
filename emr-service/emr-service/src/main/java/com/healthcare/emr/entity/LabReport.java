package com.healthcare.emr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lab_reports")
public class LabReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long medicalRecordId;

    private String testName;
    private String result;
    private String reportDate;

    public LabReport() {
    }

    public LabReport(Long medicalRecordId, String testName,
                     String result, String reportDate) {
        this.medicalRecordId = medicalRecordId;
        this.testName = testName;
        this.result = result;
        this.reportDate = reportDate;
    }

    public Long getId() {
        return id;
    }

    public Long getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
