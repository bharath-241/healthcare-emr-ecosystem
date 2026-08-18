package com.healthcare.appointment.dto;

public class AppointmentResponse {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String reason;

    public AppointmentResponse() {
    }

    public AppointmentResponse(Long id, Long patientId, Long doctorId,
                                String appointmentDate, String appointmentTime,
                                String status, String reason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
