package com.healthcare.emr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long medicalRecordId;

    private String medicineName;
    private String dosage;
    private String duration;

    public Prescription() {
    }

    public Prescription(Long medicalRecordId, String medicineName,
                         String dosage, String duration) {
        this.medicalRecordId = medicalRecordId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.duration = duration;
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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
