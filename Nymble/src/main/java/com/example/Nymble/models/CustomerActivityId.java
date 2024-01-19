package com.example.Nymble.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CustomerActivityId implements Serializable {

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "activity_id")
    private Long activityId;

}
