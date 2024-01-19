package com.example.Nymble.services;

import com.example.Nymble.models.*;
import com.example.Nymble.repo.ActivityRepo;
import com.example.Nymble.repo.CustomerActivityRepo;
import com.example.Nymble.repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerActivityServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerActivityService customerActivityService;


    @Test
    public void testTotalCost() {
        long activityId1 = 1L;
        long activityId2 = 2L;

        AddCustomer addCustomer = new AddCustomer();
        addCustomer.setCustomerId(1L);
        addCustomer.setActivity(Arrays.asList(activityId1, activityId2));

        double cost1 = 10.0;
        double cost2 = 15.0;

        when(jdbcTemplate.queryForObject(anyString(), eq(Double.class), eq(activityId1)))
                .thenReturn(cost1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Double.class), eq(activityId2)))
                .thenReturn(cost2);

        double totalCost = customerActivityService.totalCost(addCustomer);

        assertEquals(cost1 + cost2, totalCost);
    }

}
