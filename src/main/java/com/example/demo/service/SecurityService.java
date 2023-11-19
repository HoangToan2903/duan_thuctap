package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String findLoggedInUserName();
    void autoLogin(String email, String password);
}
