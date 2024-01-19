package com.example.Nymble.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagePassengerQuery {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackagePassengerQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void printTravelPackageDetails() {
        String sql = "SELECT " +
                "tp.name AS package_name, " +
                "tp.customer_capacity AS passenger_capacity, " +
                "COUNT(c.customer_id) AS num_passengers_enrolled, " +
                "c.name AS passenger_name, " +
                "c.customer_id AS passenger_number " +
                "FROM travel_package tp " +
                "JOIN customer c ON tp.travel_package_id = c.travel_package_id " +
                "GROUP BY tp.name, tp.customer_capacity, c.customer_id, c.name";

        List<Object[]> results = jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Object[]{
                        resultSet.getString("package_name"),
                        resultSet.getInt("passenger_capacity"),
                        resultSet.getInt("num_passengers_enrolled"),
                        resultSet.getString("passenger_name"),
                        resultSet.getLong("passenger_number")
                });

        for (Object[] result : results) {
            System.out.println("Package Name: " + result[0]);
            System.out.println("Passenger Capacity: " + result[1]);
            System.out.println("Num Passengers Enrolled: " + result[2]);
            System.out.println("Passenger Name: " + result[3]);
            System.out.println("Passenger Number: " + result[4]);
            System.out.println("-------------------------");
        }
    }

    public void printPassengerDetails(Long customerId) {
        String sql = "SELECT " +
                "c.name AS passenger_name, " +
                "c.customer_id AS passenger_number, " +
                "c.balance AS passenger_balance, " +
                "a.name AS activity_name, " +
                "a.activity_id AS activity_id, " +
                "a.destination_id AS destination_id, " +
                "d.name AS destination_name, " +
                "a.cost AS activity_cost " +
                "FROM customer c " +
                "JOIN customer_activity ca ON c.customer_id = ca.customer_id " +
                "JOIN activity a ON ca.activity_id = a.activity_id " +
                "JOIN destinations d ON a.destination_id = d.destination_id " +
                "WHERE c.customer_id = ?";

        List<Object[]> results = jdbcTemplate.query(sql, new Object[]{customerId}, (resultSet, rowNum) ->
                new Object[]{
                        resultSet.getString("passenger_name"),
                        resultSet.getLong("passenger_number"),
                        resultSet.getDouble("passenger_balance"),
                        resultSet.getString("activity_name"),
                        resultSet.getLong("activity_id"),
                        resultSet.getLong("destination_id"),
                        resultSet.getString("destination_name"),
                        resultSet.getDouble("activity_cost")
                });

        for (Object[] result : results) {
            System.out.println("Passenger Name: " + result[0]);
            System.out.println("Passenger Number: " + result[1]);
            System.out.println("Passenger Balance: " + result[2]);
            System.out.println("Activity Name: " + result[3]);
            System.out.println("Activity ID: " + result[4]);
            System.out.println("Destination ID: " + result[5]);
            System.out.println("Destination Name: " + result[6]);
            System.out.println("Activity Cost: " + result[7]);
            System.out.println("-------------------------");
        }
    }

    public void printActivitiesWithPositiveCapacity() {
        String sql = "SELECT name, description, curr_capacity FROM activity WHERE curr_capacity > 0";

        List<Object[]> results = jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Object[]{
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("curr_capacity")
                });

        for (Object[] result : results) {
            System.out.println("Name: " + result[0]);
            System.out.println("Description: " + result[1]);
            System.out.println("Current Capacity: " + result[2]);
            System.out.println("-------------------------");
        }
    }
}
