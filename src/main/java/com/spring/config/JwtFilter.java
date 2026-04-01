package com.spring.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spring.entity.User;
import com.spring.repository.UserRepository;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        
        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                String email = jwtUtil.extractEmail(token);

                if (email != null && jwtUtil.validateToken(token, email)) {

                    User user = userRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    List.of(new SimpleGrantedAuthority(user.getRole().name()))
                            );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                System.out.println("JWT Error: " + e.getMessage()); 
            }
        }
        filterChain.doFilter(request, response);
}
}