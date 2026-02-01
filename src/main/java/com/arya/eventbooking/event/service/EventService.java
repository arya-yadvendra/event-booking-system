package com.arya.eventbooking.event.service;

import com.arya.eventbooking.event.entity.Event;
import com.arya.eventbooking.event.entity.EventSeat;
import com.arya.eventbooking.event.enums.SeatStatus;
import com.arya.eventbooking.event.repository.EventRepository;
import com.arya.eventbooking.event.repository.EventSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepo;
    private final EventSeatRepository eventSeatRepo;

    @Transactional
    public Event createEvent(Event event) {

        Event savedEvent = eventRepo.save(event);

        // Generate event-seat availability
        savedEvent.getVenue()
                .getSections()
                .forEach(section ->
                        section.getSeats().forEach(seat ->
                                eventSeatRepo.save(
                                        EventSeat.builder()
                                                .event(savedEvent)
                                                .seat(seat)
                                                .status(SeatStatus.AVAILABLE)
                                                .build()
                                )
                        )
                );

        return savedEvent;
    }

    public List<Event> getActiveEvents() {
        return eventRepo.findByActiveTrue();
    }
}