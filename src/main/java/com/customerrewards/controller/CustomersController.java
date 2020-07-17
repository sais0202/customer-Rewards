package com.customerrewards.controller;

import com.customerrewards.domain.CustomerTransactionHistory;
import com.customerrewards.repository.CustomerRepository;
import com.customerrewards.service.CustomerService;
import com.customerrewards.model.CustomersModel;
import com.customerrewards.util.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private CustomerService customerRewardsService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards")
    public CustomersModel getCustomerRewards(@PathVariable("customerId") Long customerId) {

        Date currentDate = new Date();
        Date thirtyDaysOffsetDate = getDatePriorToDays(Values.THIRTY_DAY_OFFSET);
        Date sixtyDaysOffsetDate = getDatePriorToDays(Values.SIXTY_DAY_OFFSET);
        Date ninetyDaysOffsetDate = getDatePriorToDays(Values.NINTEY_DAY_OFFSET);

        List<CustomerTransactionHistory> thirtyDayTranscactions = customerRepository.findByCustomerIdAndTransactionDateBetween(customerId, thirtyDaysOffsetDate, currentDate);
        List<CustomerTransactionHistory> sixtyDayTransactions = customerRepository.findByCustomerIdAndTransactionDateBetween(customerId, sixtyDaysOffsetDate, thirtyDaysOffsetDate);
        List<CustomerTransactionHistory> ninetyDayTransactions = customerRepository.findByCustomerIdAndTransactionDateBetween(customerId, ninetyDaysOffsetDate, sixtyDaysOffsetDate);

        return calculateCustomerRewards(customerId, thirtyDayTranscactions, sixtyDayTransactions, ninetyDayTransactions);
    }

    @GetMapping(value = "/{customerId}/transactions")
    public List<CustomerTransactionHistory> getAllTransactionsByCustomerId(@PathVariable("customerId") Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomerTransactionsById(@PathVariable("customerId") Long customerId) {
        customerRepository.deleteByCustomerId(customerId);
    }

    private Date getDatePriorToDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    private CustomersModel calculateCustomerRewards(Long customerId, List<CustomerTransactionHistory> thirtyDayTransactions, List<CustomerTransaction> sixtyDayTransactions, List<CustomerTransaction> ninetyDayTransactions) {
        CustomersModel customerRewards = new CustomersModel();
        customerRewards.setCustomerId(customerId);
        customerRewards.setFirstMonthRewards(customerRewardsService.calculate(thirtyDayTransactions));
        customerRewards.setSecondMonthRewards(customerRewardsService.calculate(sixtyDayTransactions));
        customerRewards.setLastMonthRewards(customerRewardsService.calculate(ninetyDayTransactions));
        customerRewards.setCompleteRewardPoints(customerRewards.getFirstMonthRewards()
                + customerRewards.getSecondMonthRewards() + customerRewards.getLastMonthRewardPoints());
        return customerRewards;
    }
}