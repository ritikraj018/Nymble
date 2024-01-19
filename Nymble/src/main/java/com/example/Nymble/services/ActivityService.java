package com.example.Nymble.services;

import com.example.Nymble.models.CustomQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActivityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Your other methods...
    public void decrementCurrCapacity(long activityId) {
        String sql = "UPDATE activity SET curr_capacity = curr_capacity - 1 WHERE activity_id = ?";
        jdbcTemplate.update(sql, activityId);
    }

    public void decrementCurrCapacityTravelPackage(long travelPackageId) {
        String sql = "UPDATE travel_package SET customer_capacity = customer_capacity - 1 WHERE travel_package_id = ?";
        jdbcTemplate.update(sql, travelPackageId);
    }
    public Object executeCustomQuery() {
        String sql = "SELECT " +
                "tp.name AS travel_package_name, " +
                "d.name AS destination_name, " +
                "a.name AS activity_name, " +
                "a.cost AS activity_cost, " +
                "a.capacity AS activity_capacity, " +
                "a.description AS activity_description " +
                "FROM " +
                "travel_package tp " +
                "JOIN " +
                "destinations d ON tp.travel_package_id = d.travel_package_id " +
                "JOIN " +
                "activity a ON d.destination_id = a.destination_id " +
                "ORDER BY " +
                "tp.name, d.name, a.name";

        List<Object> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            CustomQueryResult result = new CustomQueryResult();
            result.setTravelPackageName(rs.getString("travel_package_name"));
            result.setDestinationName(rs.getString("destination_name"));
            result.setActivityName(rs.getString("activity_name"));
            result.setActivityCost(rs.getDouble("activity_cost"));
            result.setActivityCapacity(rs.getInt("activity_capacity"));
            result.setActivityDescription(rs.getString("activity_description"));
            return result;
        });

        // Process the results as needed
        String output = "Package Name    Destination    Activity     Cost    Activity Capacity    Description";
        System.out.println(output);
        for (Object result : results) {
            System.out.println(result);
        }
        return results;
    }
}
