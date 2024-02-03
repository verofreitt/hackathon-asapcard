package com.asap.hackathonasap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asap.hackathonasap.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
