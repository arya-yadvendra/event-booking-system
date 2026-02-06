package com.arya.eventbooking.event.controller;

import com.arya.eventbooking.event.entity.Event;
import com.arya.eventbooking.event.service.EventService;
import com.arya.eventbooking.util.Constant;
import com.arya.eventbooking.util.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        GenericResponse<?> response = eventService.createEvent(event);
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> getEvents() {
        GenericResponse<?> response = eventService.getActiveEvents();
        if (response.getStatus().equals(Constant.SUCCESS)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}

