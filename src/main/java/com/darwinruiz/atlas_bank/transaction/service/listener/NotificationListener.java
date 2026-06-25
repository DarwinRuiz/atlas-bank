package com.darwinruiz.atlas_bank.transaction.service.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.darwinruiz.atlas_bank.transaction.service.event.TransactionExecutedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationListener {

    @EventListener
    public void onTransactionExecuted(TransactionExecutedEvent event) {
        log.info("Sending notification for transaction {} of type {} with amount {}", event.transactionId(),
                event.type(), event.amount());
    }
}
