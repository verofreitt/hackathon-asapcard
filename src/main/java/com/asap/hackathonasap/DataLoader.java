// DataLoader.java
package com.asap.hackathonasap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.asap.hackathonasap.service.TransactionService;



@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {
    	
    }
}