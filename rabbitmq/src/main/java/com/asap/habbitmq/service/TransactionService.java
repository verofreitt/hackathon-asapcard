package com.asap.habbitmq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asap.habbitmq.dto.TransactionDto;
import com.asap.hackathonasap.Repository.InstallmentRepository;
import com.asap.hackathonasap.Repository.PersonRepository;
import com.asap.hackathonasap.Repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    public void processTransaction(TransactionDto transactionDto) {
    	
    }

    public void processCsvTransaction(List<String[]> csvData) {
    	
    }
}

