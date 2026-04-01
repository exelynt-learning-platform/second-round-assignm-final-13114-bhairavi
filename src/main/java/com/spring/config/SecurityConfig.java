package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

        	    .requestMatchers("/auth/**").permitAll()

        	    // public GET
        	    .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()

        	    // admin only
        	    .requestMatchers("/api/products/**").hasRole("ADMIN")

        	    .requestMatchers("/api/cart/**").hasRole("USER")
        	    .requestMatchers("/api/orders/**").hasRole("USER")

        	    .requestMatchers("/payment/**").authenticated()

        	    .anyRequest().authenticated()
        	)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}