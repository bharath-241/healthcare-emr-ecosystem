package com.healthcare.patient.service;

import com.healthcare.patient.entity.Patient;
import com.healthcare.patient.repository.PatientRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // =========================================================
    // CREATE PATIENT
    // =========================================================

    public Patient createPatient(Patient patient) {

        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new RuntimeException(
                    "Patient with this email already exists"
            );
        }

        return patientRepository.save(patient);
    }

    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // =========================================================
    // GET PATIENT BY ID - REDIS CACHE
    // =========================================================

    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) {

        System.out.println("Fetching patient from MySQL for ID: " + id);

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with id: " + id
                        )
                );
    }

    // =========================================================
    // UPDATE PATIENT - REMOVE OLD CACHE
    // =========================================================

    @CacheEvict(value = "patients", key = "#id")
    public Patient updatePatient(
            Long id,
            Patient updatedPatient) {

        Patient existingPatient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Patient not found with id: " + id
                                )
                        );

        existingPatient.setName(updatedPatient.getName());

        existingPatient.setEmail(updatedPatient.getEmail());

        existingPatient.setPhone(updatedPatient.getPhone());

        existingPatient.setDateOfBirth(
                updatedPatient.getDateOfBirth()
        );

        existingPatient.setGender(
                updatedPatient.getGender()
        );

        existingPatient.setAddress(
                updatedPatient.getAddress()
        );

        return patientRepository.save(existingPatient);
    }

    // =========================================================
    // DELETE PATIENT - REMOVE CACHE
    // =========================================================

    @CacheEvict(value = "patients", key = "#id")
    public String deletePatient(Long id) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Patient not found with id: " + id
                                )
                        );

        patientRepository.delete(patient);

        return "Patient deleted successfully";
    }
}