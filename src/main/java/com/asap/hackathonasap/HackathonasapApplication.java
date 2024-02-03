package com.asap.hackathonasap;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asap.hackathonasap.Repository.InstallmentRepository;
import com.asap.hackathonasap.Repository.PersonRepository;
import com.asap.hackathonasap.Repository.TransactionRepository;
import com.asap.hackathonasap.model.Person;
import com.asap.hackathonasap.model.Transaction;

@SpringBootApplication
public class HackathonasapApplication implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired 
	private TransactionRepository transationRepository;
	
	@Autowired
	private InstallmentRepository installRepository;

	public static void main(String[] args) {
		SpringApplication.run(HackathonasapApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		try {
			processInputData("input-data.csv");
			processConciliationData("conciliation-data.csv");
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void processInputData(String filePath) throws IOException {
		List<String[]> inputRows = readCSV(filePath);
		
		for(String[] row : inputRows) {
			Person person = createOrUpdatePerson(row);
			Transaction transaction = createTransaction(row, person.getId());
			createInstallment(row, transaction.getId());
		}
	}
	
	private void processConciliationData(String filePath) throws IOException {
		List<String[]> conciliationRows = readCSV(filePath);
		
		for (String[] row : conciliationRows) {
			reconcileData(row);
		}
	}

	
	

}
