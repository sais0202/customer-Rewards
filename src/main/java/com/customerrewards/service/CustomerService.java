package com.customerrewards.service;

import java.util.List;

import com.customerrewards.domain.CustomerTransactionHistory;

public interface CustomerService {

	   Long calculate(List<CustomerTransactionHistory> customerTransactions);
	}
