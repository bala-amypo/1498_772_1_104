package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.UserAccount;

public interface AuthService {

    UserAccount register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
