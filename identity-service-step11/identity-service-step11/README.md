# Identity Service - Step 11
Port: 8081

Features:
- Registration
- Login
- JWT access token (15 minutes)
- Refresh token (7 days)
- Roles: ADMIN, DOCTOR, RECEPTIONIST, PATIENT
- MySQL + Flyway + Eureka

Database:
CREATE DATABASE healthcare_identity_db;

APIs:
POST /api/auth/register
POST /api/auth/login
POST /api/auth/refresh
