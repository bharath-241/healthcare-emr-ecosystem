CREATE TABLE invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    invoice_date VARCHAR(20),
    status VARCHAR(30)
);

CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    payment_date VARCHAR(20),
    payment_method VARCHAR(50),
    status VARCHAR(30)
);

CREATE TABLE insurance_claims (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    invoice_id BIGINT NOT NULL,
    insurance_provider VARCHAR(150),
    claim_amount DECIMAL(12,2),
    status VARCHAR(30)
);
