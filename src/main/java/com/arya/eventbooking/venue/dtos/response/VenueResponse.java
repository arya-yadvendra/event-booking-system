package com.arya.eventbooking.venue.dtos.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueResponse {

    private Long id;
    private String name;
    private String city;
    private String address;
    private List<SectionResponse> sections;
}
