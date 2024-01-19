package com.example.Nymble.controllers;

import com.example.Nymble.controllers.ItenaryController;
import com.example.Nymble.models.AddCustomer;
import com.example.Nymble.models.Customer;
import com.example.Nymble.services.ActivityService;
import com.example.Nymble.services.CustomerActivityService;
import com.example.Nymble.services.CustomerService;
import com.example.Nymble.services.PackagePassengerQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItenaryControllerTest {

    @Mock
    private ActivityService activityService;


    @Mock
    private PackagePassengerQuery packagePassengerQuery;

    @InjectMocks
    private ItenaryController itenaryController;


    @Test
    public void testPrintPackageDetails() {
        itenaryController.printPackageDetails();

        verify(activityService, times(1)).executeCustomQuery();
    }

    @Test
    public void testTravelPackageDetails() {
        itenaryController.travelPackageDetails();

        verify(packagePassengerQuery, times(1)).printTravelPackageDetails();
    }

    @Test
    public void testCustomerDetails() {
        int customerId = 1;
        itenaryController.customerDetails(customerId);

        verify(packagePassengerQuery, times(1)).printPassengerDetails(eq((long) customerId));
    }

    @Test
    public void testRemainingActivities() {
        itenaryController.remainingActivities();

        verify(packagePassengerQuery, times(1)).printActivitiesWithPositiveCapacity();
    }
}
