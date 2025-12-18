package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;

    public AuthServiceImpl(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserAccount register(UserAccount user) {
        user.setActive(true);
        // password hashing simplified
        user.setPasswordHash("HASHED_" + user.getPasswordHash());
        return repo.save(user);
    }

    @Override
    public String login(String email, String password) {
        UserAccount user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPasswordHash().equals("HASHED_" + password)) {
            throw new RuntimeException("Invalid password");
        }

        // JWT simplified
        return "JWT_TOKEN_SAMPLE";
    }
}
