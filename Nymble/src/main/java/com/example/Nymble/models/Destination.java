package com.example.Nymble.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "travel_package_id", nullable = false)
    private TravelPackage travelPackage;

}
