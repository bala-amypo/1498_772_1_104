package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long validityInMs = 3600000; // 1 hour

    public String generateToken(com.example.demo.model.UserAccount user) {
        return Jwts.builder()
                .setSubject(user.getEmail())            // Subject = email
                .claim("role", user.getRole())          // Add role
                .claim("userId", user.getId())          // Add userId
                .setIssuedAt(new Date())                // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs)) // Expiry
                .signWith(key)                          // Sign with HS256
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                   .parseClaimsJws(token)
                   .getBody()
                   .get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                   .parseClaimsJws(token)
                   .getBody()
                   .get("userId", Long.class);
    }
}
