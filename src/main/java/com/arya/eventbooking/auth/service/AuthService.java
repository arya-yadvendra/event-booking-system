package com.arya.eventbooking.auth.service;

import com.arya.eventbooking.auth.dto.*;
import com.arya.eventbooking.auth.entity.*;
import com.arya.eventbooking.auth.enums.AuthProvider;
import com.arya.eventbooking.auth.repository.*;
import com.arya.eventbooking.util.Constant;
import com.arya.eventbooking.util.GeneralUtility;
import com.arya.eventbooking.util.GenericResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder,
                       JwtService jwtService, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @Transactional
    public GenericResponse<?> registerUser(RegisterRequest request) {

        if (userRepo.existsByEmail(request.email())) {
            return new GenericResponse<>(Constant.FAILURE, "Email already registered", null);
        }

        Role userRole = roleRepo.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .email(request.email())
                .password(encoder.encode(request.password()))
                .enabled(true)
                .provider(AuthProvider.LOCAL)
                .roles(Collections.singleton(userRole))
                .build();

        userRepo.save(user);

        return new GenericResponse<>(Constant.SUCCESS, "User created successfully!", user);
    }

    public GenericResponse<AuthResponse> login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        String accessToken = jwtService.generateToken(request.email());
        String refreshToken = jwtService.generateRefreshToken(request.email());

        return GeneralUtility.successResponse(
                "User Login successful!",
                new AuthResponse(accessToken, refreshToken)
        );
    }
}

