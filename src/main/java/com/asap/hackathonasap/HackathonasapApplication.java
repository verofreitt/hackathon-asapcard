package com.asap.hackathonasap;

import java.io.FileReader;
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
        
        
        // Print Data. 
        for (String[] row : lerTodasLinhas) { 
            for (String cell : row) { 
                System.out.print(cell + "\t"); 
            } 
            System.out.println(); 
        } 
    } 
	
}
