package com.asap.ingestaodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asap.ingestaodb.model.Person;
import com.asap.ingestaodb.repository.PersonRepository;

@Service
public class PersonService {
	
  @Autowired
  private PersonRepository personRepository;
  
	public void addNewPerson(Person person) {
            personRepository.save(person);
	}
	
	public boolean personExists(String id) {
		Person person = personRepository.findById(id).orElse(null);
		
		if (person != null) {
	        //System.out.println("Pessoa encontrada: " + person);
	        return true;
	    } else {
	        //System.out.println("Pessoa não encontrada por ID: " + id);
	        return false;
	    }
	}
  
}

