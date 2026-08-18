# Healthcare EMR Ecosystem

A Spring Boot Microservices based Healthcare EMR and Appointment Management System.

## Project Overview

The Healthcare EMR Ecosystem is designed to manage healthcare operations using a microservices architecture.

The system supports patient management, doctor management, appointment booking, electronic medical records, billing, notifications, and other healthcare-related operations.

## Technologies Used

- Java 21
- Spring Boot 3.5.6
- Spring Cloud
- Spring Data JPA
- MySQL
- Eureka Service Discovery
- OpenFeign
- Apache Kafka
- Redis
- Flyway
- Resilience4j
- Spring Boot Actuator
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Maven
- Docker
- Kubernetes

## Microservices

The planned system contains the following services:

1. API Gateway
2. Identity Service
3. Patient Service
4. Doctor Service
5. Appointment Service
6. EMR Service
7. Billing Service
8. Notification Service

## Appointment Service

The Appointment Service manages appointments between patients and doctors.

### Features

- Book appointment
- Get all appointments
- Get appointment by ID
- Get patient appointments
- Get doctor appointments
- Cancel appointment
- Reschedule appointment
- Doctor service communication using OpenFeign
- Kafka appointment events
- Resilience4j Circuit Breaker
- Resilience4j Retry
- MySQL database
- Flyway database migration
- Eureka service discovery
- Swagger/OpenAPI
- Spring Boot Actuator
- JUnit 5 and Mockito testing

## Architecture

```text
                    API Gateway
                         |
        +----------------+----------------+
        |                |                |
        v                v                v
   Patient Service   Doctor Service   Appointment Service
        |                |                |
        v                v                v
      MySQL            MySQL             MySQL
                                           |
                                           v
                                         Kafka
                                           |
                         +-----------------+----------------+
                         |                 |                |
                         v                 v                v
                    EMR Service     Billing Service   Notification
