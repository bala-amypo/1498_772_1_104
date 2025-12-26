package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userRepo;
    private final JwtTokenProvider jwtProvider;

    public AuthController(UserAccountRepository userRepo, JwtTokenProvider jwtProvider) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
    }

    // --- Existing register endpoint ---
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userRepo.save(user);
    }

    // --- Existing login endpoint (original behavior) ---
    @PostMapping("/login")
    public String login() {
        return "Successfully login";
    }

    // --- New login with token generation ---
    @PostMapping("/login-token")
    public ResponseEntity<AuthResponse> loginWithToken(@RequestBody AuthRequest request) {
        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Simple password check (you can replace with hash check)
        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtProvider.generateToken(user);

        AuthResponse response = new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
