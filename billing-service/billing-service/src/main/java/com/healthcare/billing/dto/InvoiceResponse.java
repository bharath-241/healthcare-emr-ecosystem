package com.healthcare.billing.dto;

import java.math.BigDecimal;

public class InvoiceResponse {

    private Long id;
    private Long patientId;
    private BigDecimal amount;
    private String invoiceDate;
    private String status;

    public InvoiceResponse() {
    }

    public InvoiceResponse(Long id, Long patientId, BigDecimal amount,
                           String invoiceDate, String status) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getStatus() {
        return status;
    }
}
