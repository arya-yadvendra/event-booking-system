package com.arya.eventbooking.auth.controller;

import com.arya.eventbooking.auth.dto.*;
import com.arya.eventbooking.auth.service.AuthService;
import com.arya.eventbooking.util.Constant;
import com.arya.eventbooking.util.GeneralUtility;
import com.arya.eventbooking.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            GenericResponse<?> response = authService.registerUser(request);
            if (response.getStatus().equals(Constant.SUCCESS)) {
                return ResponseEntity.ok(response);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    GeneralUtility.failureResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<?>> login(@RequestBody LoginRequest request) {
        try {
            GenericResponse<?> response = authService.login(request);
            if (response.getStatus().equals(Constant.SUCCESS)) {
                return ResponseEntity.ok(response);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    GeneralUtility.failureResponse(e.getMessage(), null)
            );
        }
    }
}

