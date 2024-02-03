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
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void processInputData(String filePath) throws IOException, CsvException {
        FileReader filereader = new FileReader(filePath); 
		
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        
        CSVReader csvReader = new CSVReaderBuilder(filereader) 
                .withCSVParser(parser) 
                .build(); 
        
        List<String[]> lerTodasLinhas= csvReader.readAll();
        
        for (String[] row : lerTodasLinhas) { 
            // Implement processing logic for input data
            // Create Person, Transaction, and Installment objects
            Person person = createOrUpdatePerson(row);
            Transaction transaction = createTransaction(row, person.getId());
            createInstallments(row, transaction.getId());
        }
 
    }
	
    private Person createOrUpdatePerson(String[] row) {
        String personId = row[2]; // Assuming Document is at index 2
        Person existingPerson = personRepository.findById(personId).orElse(null);

        if (existingPerson != null) {
            return existingPerson;
        } else {
            Person newPerson = new Person();
            newPerson.setId(personId);
            newPerson.setName(row[3]); // Assuming Name is at index 3
            newPerson.setAge(Integer.parseInt(row[4])); // Assuming Age is at index 4
            return personRepository.save(newPerson);
        }
    }
    
    private Transaction createTransaction(String[] row, String personId) {
        Transaction transaction = new Transaction();
        transaction.setId(row[0]); // Assuming Transaction ID is at index 0
        transaction.setPersonId(personId);
        transaction.setTransactionDate(row[1]); // Assuming Transaction Date is at index 1
        transaction.setAmount(Double.parseDouble(row[5])); // Assuming Amount is at index 5
        return transactionRepository.save(transaction);
    }
    
    private void createInstallments(String[] row, String transactionId) {
        int numInstallments = Integer.parseInt(row[6]); // Assuming Num. de Parcelas is at index 6
        double totalAmount = Double.parseDouble(row[5]); // Assuming Amount is at index 5
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
	
}
