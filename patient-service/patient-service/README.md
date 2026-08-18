# Healthcare EMR - Patient Service

Step 5 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8082

## Database

Create this MySQL database:

CREATE DATABASE healthcare_patient_db;

Update the username/password in application.yml if your MySQL credentials are different.

## APIs

POST   /api/patients
GET    /api/patients
GET    /api/patients/{id}
PUT    /api/patients/{id}
DELETE /api/patients/{id}

POST   /api/patients/{id}/medical-history
GET    /api/patients/{id}/medical-history
