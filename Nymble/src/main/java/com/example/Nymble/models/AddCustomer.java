package com.example.Nymble.models;

import lombok.Data;

import java.util.List;

@Data
public class AddCustomer {
    private Long customerId;
    private Long balance;
    private String name;
    private String type;
    private Long travelPackageId;
    List<Long> activity;
}
