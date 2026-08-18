package com.healthcare.emr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long medicalRecordId;

    private String diagnosisName;
    private String description;

    public Diagnosis() {
    }

    public Diagnosis(Long medicalRecordId, String diagnosisName,
                      String description) {
        this.medicalRecordId = medicalRecordId;
        this.diagnosisName = diagnosisName;
        this.description = description;
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

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
