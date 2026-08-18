# Healthcare EMR - Notification Service

Step 10 of the Enterprise Healthcare EMR & Appointment Ecosystem.

## Technology
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- MySQL
- Flyway
- Eureka Client
- Maven

## Port

8087

## Database

Create:

CREATE DATABASE healthcare_notification_db;

Update MySQL username/password in application.yml if required.

## APIs

POST /api/notifications/email
POST /api/notifications/sms

GET  /api/notifications
GET  /api/notifications/{id}

## Mock Providers

Email and SMS are mocked using console output.

Example:

MOCK EMAIL SENT TO: patient@example.com
MOCK SMS SENT TO: 9876543210
