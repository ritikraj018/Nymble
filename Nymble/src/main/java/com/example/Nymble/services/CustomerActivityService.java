package com.example.Nymble.services;

import com.example.Nymble.models.*;
import com.example.Nymble.repo.ActivityRepo;
import com.example.Nymble.repo.CustomerActivityRepo;
import com.example.Nymble.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerActivityService {
    @Autowired
    private CustomerActivityRepo customerActivityRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Double findCostByActivityId(long activityId) {
        String sql = "SELECT cost FROM activity WHERE activity_id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, activityId);
    }
    public void insertCustomerActivity(Long customerId, Long activityId) {
        // Retrieve the Customer and Activity entities
        Customer customer = customerRepo.findById(customerId).orElse(null);
        Activity activity = activityRepo.findById(activityId).orElse(null);


        if (customer != null && activity != null) {

            //If currCapacity is less than or equal to zero then return;
            if(activity.getCurrCapacity() <= 0) {
                System.out.println("Capacity full for activityID" + activity.getActivityId());
                return;
            }
            // Create a CustomerActivityId
            CustomerActivityId customerActivityId = new CustomerActivityId();
            customerActivityId.setCustomerId(customerId);
            customerActivityId.setActivityId(activityId);

            // Create a CustomerActivity entity
            CustomerActivity customerActivity = new CustomerActivity();
            customerActivity.setId(customerActivityId);
            customerActivity.setCustomer(customer);
            customerActivity.setActivity(activity);

            // Save the CustomerActivity entity
            activityService.decrementCurrCapacity(activityId);
            customerActivityRepo.save(customerActivity);
        } else {
            // Handle the case where Customer or Activity is not found
            System.out.println("Customer or activity not found");
        }
    }

    public  Double totalCost(AddCustomer addCustomer){
        List<Long> activityId = addCustomer.getActivity();
        Double cost = (double) 0L;
        for (Long activity : activityId) {
            cost += findCostByActivityId(activity);
        }
        return cost;
    }
    public void addCustomerActivity(AddCustomer addCustomer) {
        List<Long> activityId = addCustomer.getActivity();
        for (Long activity : activityId) {
            insertCustomerActivity(addCustomer.getCustomerId(),activity);
        }
    }
}
