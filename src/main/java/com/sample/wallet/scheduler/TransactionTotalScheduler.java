package com.sample.wallet.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TransactionTotalScheduler {

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void calculateTotalTransactions() {
        System.out.println("Total transactions amount: 10000");
    }
}