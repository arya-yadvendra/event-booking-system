package com.arya.eventbooking.venue.service;

import com.arya.eventbooking.util.GeneralUtility;
import com.arya.eventbooking.util.GenericResponse;
import com.arya.eventbooking.venue.dtos.CreateSectionRequest;
import com.arya.eventbooking.venue.dtos.CreateVenueRequest;
import com.arya.eventbooking.venue.entity.Seat;
import com.arya.eventbooking.venue.entity.Section;
import com.arya.eventbooking.venue.entity.Venue;
import com.arya.eventbooking.venue.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepo;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepo = venueRepository;
    }

    public GenericResponse<?> createVenue(CreateVenueRequest request) {
        Venue venue = Venue.builder()
                .name(request.getName())
                .city(request.getCity())
                .address(request.getAddress())
                .totalSeats(request.getTotalSeats())
                .build();

        return GeneralUtility.successResponse("Venue created successfully!", venueRepo.save(venue));
    }

    public GenericResponse<?> getAllVenues() {
        return GeneralUtility.successResponse("Venue list fetched successfully!", venueRepo.findAll());
    }

    @Transactional
    public GenericResponse<?> addSections(Long venueId, List<CreateSectionRequest> requests) {

        Venue venue = venueRepo.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        List<Section> sections = requests.stream()
                .map(req -> {
                    Section section = new Section();
                    section.setName(req.getName());
                    section.setVenue(venue);

                    section.setSeats(
                            req.getSeats().stream()
                                    .map(seatReq -> {
                                        Seat seat = new Seat();
                                        seat.setSeatNumber(seatReq.getSeatNumber());
                                        seat.setRow(seatReq.getRow());
                                        seat.setSection(section);
                                        return seat;
                                    }).toList()
                    );
                    return section;
                }).toList();

        venue.getSections().addAll(sections);
        return GeneralUtility.successResponse("Sections added successfully!", venueRepo.save(venue));
    }


}
