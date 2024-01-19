package com.example.Nymble.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "curr_capacity")
    private Integer currCapacity;

}
