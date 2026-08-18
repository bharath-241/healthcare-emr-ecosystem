# Healthcare EMR - Appointment Service

Step 7 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8084

## Database

Create:

CREATE DATABASE healthcare_appointment_db;

Update MySQL username/password in application.yml if required.

## APIs

POST   /api/appointments
GET    /api/appointments
GET    /api/appointments/{id}

GET    /api/appointments/patient/{patientId}
GET    /api/appointments/doctor/{doctorId}

PUT    /api/appointments/{id}/cancel
PUT    /api/appointments/{id}/reschedule

## Status

Initial appointment status is BOOKED.
Cancellation changes status to CANCELLED.
