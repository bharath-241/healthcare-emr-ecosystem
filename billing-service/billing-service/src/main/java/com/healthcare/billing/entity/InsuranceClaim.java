package com.healthcare.billing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "insurance_claims")
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long invoiceId;

    private String insuranceProvider;
    private BigDecimal claimAmount;
    private String status;

    public InsuranceClaim() {
    }

    public InsuranceClaim(Long patientId, Long invoiceId,
                          String insuranceProvider,
                          BigDecimal claimAmount,
                          String status) {
        this.patientId = patientId;
        this.invoiceId = invoiceId;
        this.insuranceProvider = insuranceProvider;
        this.claimAmount = claimAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
