package com.asap.hackathonasap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asap.hackathonasap.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

}


