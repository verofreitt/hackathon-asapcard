// RabbitMQConfig.java
package com.asap.habbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asap.habbitmq.dto.TransactionDto;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/publish")
    public void publishTransaction(@RequestBody TransactionDto transactionDto) {
    	
    }

    @PostMapping("/publish-csv")
    public void publishCsvTransaction(@RequestParam("file") MultipartFile file) {
    	
    }
}
