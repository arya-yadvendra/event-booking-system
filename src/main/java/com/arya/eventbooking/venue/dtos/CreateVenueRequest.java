package com.arya.eventbooking.venue.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVenueRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    private String address;

    private Integer totalSeats;
}
