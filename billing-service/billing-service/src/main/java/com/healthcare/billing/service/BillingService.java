package com.healthcare.billing.service;

import com.healthcare.billing.dto.InvoiceRequest;
import com.healthcare.billing.dto.InvoiceResponse;
import com.healthcare.billing.entity.InsuranceClaim;
import com.healthcare.billing.entity.Invoice;
import com.healthcare.billing.entity.Payment;
import com.healthcare.billing.repository.InsuranceClaimRepository;
import com.healthcare.billing.repository.InvoiceRepository;
import com.healthcare.billing.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;

    public BillingService(
            InvoiceRepository invoiceRepository,
            PaymentRepository paymentRepository,
            InsuranceClaimRepository insuranceClaimRepository) {

        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.insuranceClaimRepository = insuranceClaimRepository;
    }

    public InvoiceResponse createInvoice(InvoiceRequest request) {

        Invoice invoice = new Invoice(
                request.getPatientId(),
                request.getAmount(),
                request.getInvoiceDate(),
                "PENDING"
        );

        return convertToResponse(invoiceRepository.save(invoice));
    }

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public InvoiceResponse getInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return convertToResponse(invoice);
    }

    public Payment createPayment(Payment payment) {

        if (!invoiceRepository.existsById(payment.getInvoiceId())) {
            throw new RuntimeException("Invoice not found");
        }

        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public InsuranceClaim createInsuranceClaim(
            InsuranceClaim claim) {

        if (!invoiceRepository.existsById(claim.getInvoiceId())) {
            throw new RuntimeException("Invoice not found");
        }

        claim.setStatus("PENDING");
        return insuranceClaimRepository.save(claim);
    }

    public List<InsuranceClaim> getInsuranceClaims() {
        return insuranceClaimRepository.findAll();
    }

    public InsuranceClaim getInsuranceClaim(Long id) {
        return insuranceClaimRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Insurance claim not found"));
    }

    public InsuranceClaim approveInsuranceClaim(Long id) {

        InsuranceClaim claim = getInsuranceClaim(id);
        claim.setStatus("APPROVED");

        return insuranceClaimRepository.save(claim);
    }

    private InvoiceResponse convertToResponse(Invoice invoice) {

        return new InvoiceResponse(
                invoice.getId(),
                invoice.getPatientId(),
                invoice.getAmount(),
                invoice.getInvoiceDate(),
                invoice.getStatus()
        );
    }
}
