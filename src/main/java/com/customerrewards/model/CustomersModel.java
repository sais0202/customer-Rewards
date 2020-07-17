package com.customerrewards.model;

public class CustomersModel {

	private Long customerId;
    private Long firstMonthRewards;
    private Long secondMonthRewards;
    private Long lastMonthRewards;
    private Long completeRewardPoints;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFirstMonthRewards() {
        return firstMonthRewards;
    }

    public void setFirstMonthRewards(Long firstMonthRewards) {
        this.firstMonthRewards = firstMonthRewards;
    }

    public Long getSecondMonthRewards() {
        return secondMonthRewards;
    }

    public void setSecondMonthRewards(Long secondMonthRewards) {
        this.secondMonthRewards = secondMonthRewards;
    }

    public Long getLastMonthRewardPoints() {
        return lastMonthRewards;
    }

    public void setLastMonthRewads(Long lastMonthRewards) {
        this.lastMonthRewards = lastMonthRewards;
    }

    public Long getCompleteRewardPoints() {
        return completeRewardPoints;
    }

    public void setCompleteRewardPoints(Long completeRewardPoints) {
        this.completeRewardPoints = completeRewardPoints;
    }

    @Override
    public String toString() {
        return "Rewards {" +"customerId=" + customerId + ", FirstMonthRewards=" + firstMonthRewards +
                ", SecondMonthRewards=" + secondMonthRewards +
                ", LastMonthRewards=" + lastMonthRewards +
                ", CompleteRewardPoints=" + completeRewardPoints +
                '}';
    }

	

}

