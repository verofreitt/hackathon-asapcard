package com.asap.ingestaodb.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.asap.ingestaodb.MensageriaTransacoes;
import com.asap.ingestaodb.service.InstallmentService;
import com.asap.ingestaodb.service.PersonService;
import com.asap.ingestaodb.service.TransactionService;

@Component
public class WriterInputData implements CommandLineRunner {
	
	private final PersonService personService;
	private final TransactionService transactionService;
	private final InstallmentService installmentService;
	
	@Autowired
    public WriterInputData(PersonService personController, TransactionService transactionService, 
    		InstallmentService installmentService) {
        this.personService = personController;
        this.transactionService = transactionService;
        this.installmentService = installmentService;
    }
	
    @Override
    public void run(String... args) throws Exception {
    	MensageriaTransacoes mensageria = new MensageriaTransacoes(personService, transactionService, installmentService);
		mensageria.consume();
    }
}