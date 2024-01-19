package com.example.Nymble.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CustomerActivity")
public class CustomerActivity {

        @EmbeddedId
        private CustomerActivityId id;

        @ManyToOne
        @MapsId("customer_id")
        @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
        private Customer customer;

        @ManyToOne
        @MapsId("activityId")
        @JoinColumn(name = "activity_id", referencedColumnName = "activity_id", nullable = false)
        private Activity activity;

}
