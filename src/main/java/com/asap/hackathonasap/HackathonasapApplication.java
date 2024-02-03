package com.asap.hackathonasap;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asap.hackathonasap.Repository.InstallmentRepository;
import com.asap.hackathonasap.Repository.PersonRepository;
import com.asap.hackathonasap.Repository.TransactionRepository;
import com.asap.hackathonasap.model.Installment;
import com.asap.hackathonasap.model.Person;
import com.asap.hackathonasap.model.Transaction;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@SpringBootApplication
public class HackathonasapApplication implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired 
	private TransactionRepository transactionRepository;
	
	@Autowired
	private InstallmentRepository installmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(HackathonasapApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        try {
            processInputData("input-data.csv");
            processConciliationData("conciliation-data.csv");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
	
	private void processInputData(String filePath) throws IOException, CsvException {
	    FileReader filereader = new FileReader(filePath);
	    CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	    CSVReader csvReader = new CSVReaderBuilder(filereader)
	            .withCSVParser(parser)
	            .build();
	    List<String[]> inputRows = csvReader.readAll();

	    for (String[] row : inputRows) {
	        Person person = createOrUpdatePerson(row);
	        Transaction transaction = createTransaction(row, person.getId());
	        System.out.print(person);
	        System.out.print(transaction);
	        
	        transaction.setStatus("PENDING"); 

	        createInstallments(row, transaction.getId());
	        
	    }
	}

	private void processConciliationData(String filePath) throws IOException, CsvException {
	    FileReader filereader = new FileReader(filePath);
	    CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	    CSVReader csvReader = new CSVReaderBuilder(filereader)
	            .withCSVParser(parser)
	            .build();
	    List<String[]> conciliationRows = csvReader.readAll();

	    for (String[] row : conciliationRows) {
	        String transactionId = row[0];
	        String status = row[3]; 

	        Transaction storedTransaction = transactionRepository.findById(transactionId).orElse(null);

	        if (storedTransaction != null) {
	            storedTransaction.setStatus(status);

	            transactionRepository.save(storedTransaction);
	        } else {
	            System.out.println("Transaction with ID " + transactionId + " not found in the database.");
	        }
	    }
	}

	    private Person createOrUpdatePerson(String[] row) {
	        String personId = row[2];
	        Person existingPerson = personRepository.findById(personId).orElse(null);

	        if (existingPerson != null) {
	            return existingPerson;
	        } else {
	            Person newPerson = new Person();
	            newPerson.setId(personId);
	            newPerson.setName(row[3]); 
	            newPerson.setAge(Integer.parseInt(row[4]));
	            return personRepository.save(newPerson);
	        }
	    }

	    private Transaction createTransaction(String[] row, String personId) {
	        Transaction transaction = new Transaction();
	        transaction.setId(row[0]);
	        transaction.setPersonId(personId);
	        transaction.setTransactionDate(row[1]);
	        transaction.setAmount(Double.parseDouble(row[5]));

	        String status = "PENDING";
	        transaction.setStatus(status);

	        if (status != null) {
	            transactionRepository.save(transaction);
	        } else {
	            System.out.println("Status is null. Cannot save transaction.");
	        }
	        return transaction;
	    }

	    private void createInstallments(String[] row, String transactionId) {
	        int numInstallments = Integer.parseInt(row[6]); 
	        double totalAmount = Double.parseDouble(row[5]); 
	        double installmentValue = totalAmount / numInstallments;

	        for (int i = 1; i <= numInstallments; i++) {
	            Installment installment = new Installment();
	            installment.setId(UUID.randomUUID().toString());
	            installment.setTransactionId(transactionId);
	            installment.setInstallmentNumber(i);
	            installment.setValue(installmentValue);
	            installmentRepository.save(installment);
	        }
	    }

	    private void reconcileData(String[] row) {
	        String transactionId = row[0]; 
	        double transactionAmountCSV = Double.parseDouble(row[1]); 

	        Transaction storedTransaction = transactionRepository.findById(transactionId).orElse(null);

	        if (storedTransaction != null) {
	            double storedTransactionAmount = storedTransaction.getAmount();

	            if (transactionAmountCSV == storedTransactionAmount) {
	                storedTransaction.setConciliationStatus("CONCILIATED");
	            } else {
	                storedTransaction.setConciliationStatus("NOT CONCILIATED");
	            }

	            transactionRepository.save(storedTransaction);
	        } else {
	            System.out.println("Transaction with ID " + transactionId + " not found in the database.");
	        }

	    }
}
