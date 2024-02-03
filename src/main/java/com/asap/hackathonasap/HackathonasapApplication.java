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
		// TODO Auto-generated method stub
		
	}
	
	private void processInputData(String filePath) throws IOException {
		
		
	}

	
	

}
