package com.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.entity.AuthRequest;
import com.spring.entity.User;
import com.spring.service.AuthService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    // User Register
    @PostMapping("/register")
    public User register( @Valid @RequestBody User user) {
        return service.register(user);
    }

    // 
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest request) {
        return service.login(request);
    }
}