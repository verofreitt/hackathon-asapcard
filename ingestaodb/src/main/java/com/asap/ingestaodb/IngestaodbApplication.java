package com.asap.ingestaodb;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asap.ingestaodb.repository.PersonRepository;

@SpringBootApplication
public class IngestaodbApplication {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(IngestaodbApplication.class, args);		
	}

}
