// RabbitMQConsumer.java
package com.asap.habbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asap.habbitmq.dto.TransactionDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQConsumer {

    @Autowired
    private TransactionService transactionService;

    @RabbitListener(queues = "transactions-queue")
    public void receiveMessage(String message) {
    	
    }
}
