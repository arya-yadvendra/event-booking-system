package com.arya.eventbooking.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}
