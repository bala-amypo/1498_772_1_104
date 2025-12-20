package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoints")
public class AuthController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* ---------------- REGISTER ---------------- */

    @Operation(summary = "Register a new user account")
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @Valid @RequestBody UserAccount user
    ) {

        if (userAccountRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        // hash password
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        user.setActive(true);

        UserAccount savedUser = userAccountRepository.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /* ---------------- LOGIN ---------------- */

    @Operation(summary = "Login using email and password")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> request
    ) {

        String email = request.get("email");
        String password = request.get("password");

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.getActive()) {
            throw new BadRequestException("User account inactive");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }

        // Fake JWT token (enough for tests)
        String token = "mock-jwt-token-" + user.getId();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
