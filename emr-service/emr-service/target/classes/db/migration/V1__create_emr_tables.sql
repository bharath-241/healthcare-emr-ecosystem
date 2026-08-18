CREATE TABLE medical_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    visit_date VARCHAR(20),
    symptoms VARCHAR(500),
    notes VARCHAR(1000)
);

CREATE TABLE diagnoses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medical_record_id BIGINT NOT NULL,
    diagnosis_name VARCHAR(200),
    description VARCHAR(500)
);

CREATE TABLE prescriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medical_record_id BIGINT NOT NULL,
    medicine_name VARCHAR(200),
    dosage VARCHAR(100),
    duration VARCHAR(100)
);

CREATE TABLE lab_reports (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medical_record_id BIGINT NOT NULL,
    test_name VARCHAR(200),
    result VARCHAR(500),
    report_date VARCHAR(20)
);