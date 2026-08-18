# Healthcare EMR - Billing Service

Step 9 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8086

## Database

Create:

CREATE DATABASE healthcare_billing_db;

Update MySQL username/password in application.yml if required.

## APIs

POST /api/billing/invoices
GET  /api/billing/invoices
GET  /api/billing/invoices/{id}

POST /api/billing/payments
GET  /api/billing/payments/{id}

POST /api/billing/insurance-claims
GET  /api/billing/insurance-claims
GET  /api/billing/insurance-claims/{id}

PUT /api/billing/insurance-claims/{id}/approve
