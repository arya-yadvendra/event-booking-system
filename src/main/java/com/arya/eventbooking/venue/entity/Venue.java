package com.arya.eventbooking.venue.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;

    private Integer totalSeats;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Section> sections;
}
