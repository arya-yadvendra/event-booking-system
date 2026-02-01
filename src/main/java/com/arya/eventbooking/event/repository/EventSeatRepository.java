package com.arya.eventbooking.event.repository;

import com.arya.eventbooking.event.entity.EventSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSeatRepository extends JpaRepository<EventSeat, Long> {
}
