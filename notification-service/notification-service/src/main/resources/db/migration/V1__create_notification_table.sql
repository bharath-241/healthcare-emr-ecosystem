CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    recipient VARCHAR(150) NOT NULL,
    subject VARCHAR(200),
    message VARCHAR(1000),
    status VARCHAR(30),
    created_at VARCHAR(50)
);
