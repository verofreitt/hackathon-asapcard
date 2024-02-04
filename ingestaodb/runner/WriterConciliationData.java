package com.asap.ingestaodb.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.asap.ingestaodb.MensageriaConciliacoes;
import com.asap.ingestaodb.service.TransactionService;


@Component
public class WriterConciliationData implements CommandLineRunner {
	
	private final TransactionService transactionService;
	
	@Autowired
    public WriterConciliationData(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
	
    @Override
    public void run(String... args) throws Exception {
    	MensageriaConciliacoes mensageria = new MensageriaConciliacoes(transactionService);
		mensageria.consume();
    }
}