package com.healthcare.billing.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InvoiceRequest {

    @NotNull
    private Long patientId;

    @NotNull
    private BigDecimal amount;

    private String invoiceDate;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
