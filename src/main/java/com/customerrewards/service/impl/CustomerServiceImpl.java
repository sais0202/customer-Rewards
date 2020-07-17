package com.customerrewards.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import com.customerrewards.domain.CustomerTransactionHistory;
import com.customerrewards.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    public static final int TWO_POINTS = 2;

    @Value("${rewards.single.point.amount}")
    private Integer singlePointAmount;

    @Value("${rewards.double.point.amount}")
    private Integer doublePointAmount;

    public Long calculate(List<CustomerTransactionHistory> customerTransactions) {
        return customerTransactions.stream().map(customerTransaction -> calculateRewardPoints(customerTransaction))
                .collect(Collectors.summingLong(rewards -> rewards));
    }

    private Long calculateRewardPoints(CustomerTransactionHistory customerTransaction) {
        long rewardPoints = 0;

        if (customerTransaction.getTransactionAmount() > singlePointAmount && customerTransaction.getTransactionAmount() <= doublePointAmount) {
            rewardPoints = Math.round(customerTransaction.getTransactionAmount() - singlePointAmount);
        } else if (customerTransaction.getTransactionAmount() > doublePointAmount) {
            rewardPoints = singlePointAmount + TWO_POINTS * Math.round(customerTransaction.getTransactionAmount() - doublePointAmount);
        }

        return rewardPoints;
    }

}
