package com.arya.eventbooking.venue.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSectionRequest {

    @NotBlank
    private String name; // VIP, GOLD, SILVER

    @NotEmpty
    private List<CreateSeatRequest> seats;
}
