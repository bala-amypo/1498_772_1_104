package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoints")
public class AuthController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @RequestBody UserAccount user
    ) {
        if (userAccountRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        user.setActive(true);

        UserAccount saved = userAccountRepository.save(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

   

    @Operation(summary = "Login user")
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
            throw new BadRequestException("not active");
        }

        // SIMPLE password check (NO security)
        if (!password.equals(user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", "mock-token-" + user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
