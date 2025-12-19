package com.example.demo.service;

import com.example.demo.model.UserAccount;

public interface AuthService {

    UserAccount register(UserAccount user);
    String login(String email, String password);
}
