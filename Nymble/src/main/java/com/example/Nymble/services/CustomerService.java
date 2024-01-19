package com.example.Nymble.services;

import com.example.Nymble.models.AddCustomer;
import com.example.Nymble.models.Customer;
import com.example.Nymble.models.TravelPackage;
import com.example.Nymble.repo.ActivityRepo;
import com.example.Nymble.repo.CustomerRepo;
import com.example.Nymble.repo.TravelPackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TravelPackageRepo travelPackageRepo;

    public String addCustomerActivities(AddCustomer addCustomer){

        System.out.println(addCustomer.getCustomerId());

        return "balance";
    }


    public Customer addCustomer(AddCustomer addCustomer,Double cost) {
        TravelPackage travelPackageId = travelPackageRepo.findById(addCustomer.getTravelPackageId()).orElse(null);
        Customer customer = new Customer(addCustomer.getCustomerId(),addCustomer.getBalance(),addCustomer.getName(),
                addCustomer.getType(),travelPackageId);
        long finalCost = (long) calculateCost(addCustomer.getType(),cost);
        activityService.decrementCurrCapacityTravelPackage(addCustomer.getTravelPackageId());
        customer.setBalance(customer.getBalance()-finalCost);
        return customerRepo.save(customer);
    }

    static double calculateCost(String type, Double cost) {
        if (Objects.equals(type, "GOLD")) {
            return cost * 0.9; // Apply 10% discount for Gold Customers
        } else if(Objects.equals(type, "PREMIUM")){
            return 0;                       // Charge Free for Premium Customers
        } else {
            return cost;
        }
    }
}
