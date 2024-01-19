package com.example.Nymble.controllers;

import com.example.Nymble.models.AddCustomer;
import com.example.Nymble.models.Customer;
import com.example.Nymble.services.ActivityService;
import com.example.Nymble.services.CustomerActivityService;
import com.example.Nymble.services.CustomerService;
import com.example.Nymble.services.PackagePassengerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItenaryController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CustomerActivityService customerActivityService;

    @Autowired
    private PackagePassengerQuery packagePassengerQuery;

    @PostMapping("/add")
    public String addPassengerWithActivities(@RequestBody AddCustomer addCustomer) {
        Double cost = customerActivityService.totalCost(addCustomer);
        if(cost > addCustomer.getBalance()) return "Not Enough Balance, Please Add Money.";
        Customer savedCustomer = customerService.addCustomer(addCustomer,cost);
        customerActivityService.addCustomerActivity(addCustomer);
        return customerService.addCustomerActivities(addCustomer);
    }

    @GetMapping("/package_details")
    public void
    printPackageDetails(){
        activityService.executeCustomQuery();
    }

    @GetMapping("/passenger_list")
    public void travelPackageDetails() {
        packagePassengerQuery.printTravelPackageDetails();
    }

    @GetMapping("/customer_details")
    public void customerDetails(@RequestParam Integer id) {
        packagePassengerQuery.printPassengerDetails(Long.valueOf(id));
    }

    @GetMapping("/activities")
    public void remainingActivities() {
        packagePassengerQuery.printActivitiesWithPositiveCapacity();
    }
}
