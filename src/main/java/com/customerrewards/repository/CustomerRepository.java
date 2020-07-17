package com.customerrewards.repository;

import java.util.Date;
import java.util.List;

import com.customerrewards.domain.CustomerTransactionHistory;

public interface CustomerRepository {

    List<CustomerTransactionHistory> findByCustomerIdAndTransactionDateBetween(Long customerId, Date fromDate, Date toDate);

    List<CustomerTransactionHistory> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}