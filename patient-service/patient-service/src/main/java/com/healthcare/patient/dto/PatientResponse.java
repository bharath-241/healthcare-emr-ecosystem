package com.healthcare.patient.dto;

public class PatientResponse {

    private Long id;
    private String name;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String gender;

    public PatientResponse() {
    }

    public PatientResponse(Long id, String name, String dateOfBirth,
                           String email, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}
