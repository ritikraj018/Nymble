package com.example.Nymble.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private double balance;
    private String type;

    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

    public Customer(){

    }

    public Customer(Long customerId, Long balance, String name, String type, TravelPackage travelPackageId) {
        this.customerId = customerId;
        this.balance = balance;
        this.name = name;
        this.type = type;
        this.travelPackage = travelPackageId;
    }
}
