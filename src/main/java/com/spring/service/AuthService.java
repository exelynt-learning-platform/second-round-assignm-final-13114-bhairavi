package com.spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.entity.AuthRequest;
import com.spring.entity.Role;
import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.config.JwtUtil;  

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;   

    // Register 
    public User register(User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        if(user.getEmail().equals("admin@gmail.com")){
            user.setRole(Role.ROLE_ADMIN);
            System.out.println(" Assigned ROLE_ADMIN");
        } else {
            user.setRole(Role.ROLE_USER);
            System.out.println(" Assigned ROLE_USER");
        }

        return repo.save(user);
    }

    // Login
    public Map<String, String> login(AuthRequest request) {

        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of("token", token);
    }
}
