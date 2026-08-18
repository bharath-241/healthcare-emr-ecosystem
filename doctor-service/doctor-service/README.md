# Healthcare EMR - Doctor Service

Step 6 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8083

## Database

Create:

CREATE DATABASE healthcare_doctor_db;

Update MySQL username/password in application.yml if required.

## APIs

POST   /api/doctors
GET    /api/doctors
GET    /api/doctors/{id}
PUT    /api/doctors/{id}
DELETE /api/doctors/{id}

POST   /api/doctors/{id}/schedule
GET    /api/doctors/{id}/schedule

POST   /api/doctors/{id}/leave
GET    /api/doctors/{id}/leave
