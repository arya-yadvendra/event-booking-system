package com.arya.eventbooking.venue.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionResponse {

    private Long id;
    private String name;
    private int totalSeats;
}
