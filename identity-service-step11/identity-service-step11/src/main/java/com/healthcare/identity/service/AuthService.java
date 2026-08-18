package com.healthcare.identity.service;

import com.healthcare.identity.dto.*;
import com.healthcare.identity.entity.*;
import com.healthcare.identity.repository.*;
import com.healthcare.identity.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository users;
    private final RefreshTokenRepository tokens;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(
            UserRepository u,
            RefreshTokenRepository t,
            PasswordEncoder e,
            JwtService j) {

        users = u;
        tokens = t;
        encoder = e;
        jwt = j;
    }

    // =========================
    // REGISTER
    // =========================
    public String register(RegisterRequest r) {

        if (users.existsByEmail(r.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        String role = r.getRole() == null || r.getRole().isBlank()
                ? "PATIENT"
                : r.getRole().toUpperCase();

        if (!role.matches("ADMIN|DOCTOR|RECEPTIONIST|PATIENT")) {
            throw new RuntimeException("Invalid role");
        }

        users.save(
            new User(
                r.getName(),
                r.getEmail(),
                encoder.encode(r.getPassword()),
                role
            )
        );

        return "User registered successfully";
    }

    // =========================
    // LOGIN
    // =========================
    @Transactional
    public LoginResponse login(LoginRequest r) {

        User u = users.findByEmail(r.getEmail())
                .orElseThrow(() ->
                    new RuntimeException("Invalid email or password")
                );

        if (!encoder.matches(
                r.getPassword(),
                u.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        // Delete old refresh token
        tokens.deleteByEmail(u.getEmail());

        // Generate access token
        String access =
                jwt.generateToken(
                    u.getEmail(),
                    u.getRole()
                );

        // Generate refresh token
        String refresh =
                UUID.randomUUID().toString();

        // Save new refresh token
        tokens.save(
            new RefreshToken(
                refresh,
                u.getEmail(),
                LocalDateTime.now().plusDays(7)
            )
        );

        return new LoginResponse(
            "Login successful",
            access,
            refresh
        );
    }

    // =========================
    // REFRESH TOKEN
    // =========================
    public LoginResponse refresh(RefreshTokenRequest r) {

        RefreshToken t =
                tokens.findByToken(
                    r.getRefreshToken()
                ).orElseThrow(() ->
                    new RuntimeException(
                        "Invalid refresh token"
                    )
                );

        if (t.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                "Refresh token expired"
            );
        }

        User u =
                users.findByEmail(t.getEmail())
                .orElseThrow(() ->
                    new RuntimeException(
                        "User not found"
                    )
                );

        return new LoginResponse(
            "Token refreshed",
            jwt.generateToken(
                u.getEmail(),
                u.getRole()
            ),
            t.getToken()
        );
    }
}