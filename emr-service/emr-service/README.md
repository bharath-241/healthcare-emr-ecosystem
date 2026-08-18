# Healthcare EMR - EMR Service

Step 8 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8085

## Database

Create:

CREATE DATABASE healthcare_emr_db;

Update MySQL username/password in application.yml if required.

## APIs

POST /api/emr
GET  /api/emr/{id}
GET  /api/emr/patient/{patientId}
GET  /api/emr/doctor/{doctorId}

POST /api/emr/{id}/diagnosis
GET  /api/emr/{id}/diagnosis

POST /api/emr/{id}/prescription
GET  /api/emr/{id}/prescription

POST /api/emr/{id}/lab-report
GET  /api/emr/{id}/lab-report
