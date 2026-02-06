package com.arya.eventbooking.venue.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"section_id", "seat_number"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber; // A1, A2, B5
    private String row;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

