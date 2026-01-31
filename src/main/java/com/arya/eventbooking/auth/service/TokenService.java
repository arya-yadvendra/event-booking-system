package com.arya.eventbooking.auth.service;

import com.arya.eventbooking.auth.dto.AuthResponse;
import com.arya.eventbooking.util.GeneralUtility;
import com.arya.eventbooking.util.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final JwtService jwtService;

    public TokenService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public GenericResponse<?> getTokens(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        return GeneralUtility.successResponse(
                "token fetched successfully!",
                new AuthResponse(jwtService.generateToken(username), refreshToken)
        );
    }
}
