package com.example.Nymble.models;

import lombok.Data;

@Data
public class CustomQueryResult {
    private String travelPackageName;
    private String destinationName;
    private String activityName;
    private double activityCost;
    private int activityCapacity;
    private String activityDescription;

    @Override
    public String toString() {
        return  travelPackageName  + destinationName  + "    " + activityName  + "    " + activityCost +"    " + activityCapacity +"    " + activityDescription ;
    }
}

