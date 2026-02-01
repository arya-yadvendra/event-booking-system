package com.arya.eventbooking.venue.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSeatRequest {

    @NotBlank
    private String seatNumber; // A1, A2, B5

    private String row;
}
