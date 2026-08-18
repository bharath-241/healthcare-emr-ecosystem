package com.healthcare.emr.dto;

public class EmrResponse {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String visitDate;
    private String symptoms;
    private String notes;

    public EmrResponse() {
    }

    public EmrResponse(Long id, Long patientId, Long doctorId,
                        String visitDate, String symptoms, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.symptoms = symptoms;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getNotes() {
        return notes;
    }
}
