package com.healthcare.billing.controller;

import com.healthcare.billing.dto.InvoiceRequest;
import com.healthcare.billing.dto.InvoiceResponse;
import com.healthcare.billing.entity.InsuranceClaim;
import com.healthcare.billing.entity.Payment;
import com.healthcare.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponse> createInvoice(
            @Valid @RequestBody InvoiceRequest request) {

        return ResponseEntity.ok(
                billingService.createInvoice(request)
        );
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        return ResponseEntity.ok(
                billingService.getAllInvoices()
        );
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> getInvoice(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billingService.getInvoice(id)
        );
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment) {

        return ResponseEntity.ok(
                billingService.createPayment(payment)
        );
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPayment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billingService.getPayment(id)
        );
    }

    @PostMapping("/insurance-claims")
    public ResponseEntity<InsuranceClaim> createInsuranceClaim(
            @RequestBody InsuranceClaim claim) {

        return ResponseEntity.ok(
                billingService.createInsuranceClaim(claim)
        );
    }

    @GetMapping("/insurance-claims")
    public ResponseEntity<List<InsuranceClaim>> getInsuranceClaims() {
        return ResponseEntity.ok(
                billingService.getInsuranceClaims()
        );
    }

    @GetMapping("/insurance-claims/{id}")
    public ResponseEntity<InsuranceClaim> getInsuranceClaim(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billingService.getInsuranceClaim(id)
        );
    }

    @PutMapping("/insurance-claims/{id}/approve")
    public ResponseEntity<InsuranceClaim> approveInsuranceClaim(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billingService.approveInsuranceClaim(id)
        );
    }
}
