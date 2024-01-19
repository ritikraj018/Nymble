package com.example.Nymble.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "TravelPackage")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_package_id")
    private Long travelPackageId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "customer_capacity", nullable = false)
    private Integer customerCapacity;

    @Column(name ="enrolled_customers")
    private Integer numberOfEnrolledCustomers;
}
