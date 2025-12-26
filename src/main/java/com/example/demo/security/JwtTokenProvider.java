package com.example.demo.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.model.UserAccount;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtTokenProvider {

    private String secretKey;
    private int validityInMs;
    private Key key;

    
    public JwtTokenProvider(String secretKey, int validityInMs) {
        this.secretKey = secretKey;
        this.validityInMs = validityInMs;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    
    public JwtTokenProvider() {
        this.secretKey = "employee-equipment-secret-key-1234567890";
        this.validityInMs = 3600000; // 1 hour
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /* ================= TOKEN GENERATION ================= */

    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /* ================= TOKEN VALIDATION ================= */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* ================= TOKEN DATA ================= */

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
