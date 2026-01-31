package com.arya.eventbooking.auth.controller;

import com.arya.eventbooking.auth.service.TokenService;
import com.arya.eventbooking.util.Constant;
import com.arya.eventbooking.util.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        GenericResponse<?> response = tokenService.getTokens(refreshToken);
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

