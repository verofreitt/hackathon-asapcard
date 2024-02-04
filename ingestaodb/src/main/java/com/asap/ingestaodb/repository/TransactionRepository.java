package com.asap.ingestaodb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asap.ingestaodb.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

}
