package com.arya.eventbooking.venue.controller;

import com.arya.eventbooking.util.GeneralUtility;
import com.arya.eventbooking.util.GenericResponse;
import com.arya.eventbooking.venue.dtos.CreateSectionRequest;
import com.arya.eventbooking.venue.dtos.CreateVenueRequest;
import com.arya.eventbooking.venue.entity.Section;
import com.arya.eventbooking.venue.entity.Venue;
import com.arya.eventbooking.venue.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse<Venue>> createVenue(@RequestBody @Valid CreateVenueRequest request) {
        return ResponseEntity.ok(
                GeneralUtility.successResponse(
                        "Venue created",
                        venueService.createVenue(request)
                )
        );
    }


    @GetMapping
    public List<Venue> getVenues() {
        return venueService.getAllVenues();
    }

    @PostMapping("/{venueId}/sections")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse<Venue>> addSections(
            @PathVariable Long venueId,
            @RequestBody @Valid List<CreateSectionRequest> sections
    ) {
        return ResponseEntity.ok(
                GeneralUtility.successResponse(
                        "Sections added",
                        venueService.addSections(venueId, sections)
                )
        );
    }


}
