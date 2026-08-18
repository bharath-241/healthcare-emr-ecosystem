package com.healthcare.billing.repository;

import com.healthcare.billing.entity.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceClaimRepository
        extends JpaRepository<InsuranceClaim, Long> {

    List<InsuranceClaim> findByPatientId(Long patientId);
}
