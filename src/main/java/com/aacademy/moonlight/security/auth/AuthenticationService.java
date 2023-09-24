package com.aacademy.moonlight.security.auth;

import com.aacademy.moonlight.dto.security.AuthResponse;
import com.aacademy.moonlight.dto.security.LoginRequest;
import com.aacademy.moonlight.dto.security.RegisterRequest;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.entity.user.UserRole;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import com.aacademy.moonlight.security.config.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final UserRoleRepository userRoleRepository;

    public AuthResponse register(RegisterRequest request){

        UserRole role = userRoleRepository.findById(2L).orElseThrow();


         var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(encoder.encode(request.getPassword()))
                 .userRole(role)
                 .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);

                return AuthResponse.builder()
                        .token(jwtToken)
                        .build();
    }

    public AuthResponse login (LoginRequest request){
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user = repository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
