package com.example.Nymble.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Test
    public void testCalculateCostGold() {
        double cost = 100.0;
        double result = CustomerService.calculateCost("GOLD", cost);
        assertEquals(cost * 0.9, result, 0.001);
    }

    @Test
    public void testCalculateCostPremium() {
        double cost = 100.0;
        double result = CustomerService.calculateCost("PREMIUM", cost);
        assertEquals(0, result, 0.001);
    }

    @Test
    public void testCalculateCostOther() {
        double cost = 100.0;
        double result = CustomerService.calculateCost("SILVER", cost);
        assertEquals(cost, result, 0.001);
    }
}
