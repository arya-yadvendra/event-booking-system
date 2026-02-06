package com.arya.eventbooking.venue.controller;

import com.arya.eventbooking.util.Constant;
import com.arya.eventbooking.util.GenericResponse;
import com.arya.eventbooking.venue.dtos.CreateSectionRequest;
import com.arya.eventbooking.venue.dtos.CreateVenueRequest;
import com.arya.eventbooking.venue.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse<?>> createVenue(@RequestBody @Valid CreateVenueRequest request) {
        GenericResponse<?> response = venueService.createVenue(request);
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list-all")
    public ResponseEntity<GenericResponse<?>> getVenues() {
        GenericResponse<?> response = venueService.getAllVenues();
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{venueId}/add-sections")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse<?>> addSections(@PathVariable Long venueId,
                                                          @RequestBody @Valid List<CreateSectionRequest> sections) {
        GenericResponse<?> response = venueService.addSections(venueId, sections);
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
