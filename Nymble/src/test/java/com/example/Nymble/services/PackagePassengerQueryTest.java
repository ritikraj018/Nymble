package com.example.Nymble.services;

import com.example.Nymble.services.PackagePassengerQuery;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackagePassengerQueryTest {

    @Autowired
    private PackagePassengerQuery packagePassengerQuery;

    @Test
    public void testPrintTravelPackageDetails() {
        // Assuming the method doesn't throw any exceptions
        packagePassengerQuery.printTravelPackageDetails();
    }

    @Test
    public void testPrintPassengerDetails() {
        // Provide a valid customerId for testing
        Long customerId = 1L;
        packagePassengerQuery.printPassengerDetails(customerId);

        // Add assertions or further checks based on your business logic
    }

    @Test
    public void testPrintActivitiesWithPositiveCapacity() {
        // Assuming the method doesn't throw any exceptions
        packagePassengerQuery.printActivitiesWithPositiveCapacity();
    }
}
