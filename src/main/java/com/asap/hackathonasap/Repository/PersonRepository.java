package com.asap.hackathonasap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asap.hackathonasap.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}


