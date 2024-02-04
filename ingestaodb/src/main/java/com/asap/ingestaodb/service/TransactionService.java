package com.asap.ingestaodb.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asap.ingestaodb.model.Transaction;
import com.asap.ingestaodb.repository.TransactionRepository;



@Service
public class TransactionService {

	@Autowired
	  private TransactionRepository transactionRepository;
	  
		public void addNewTransaction(Transaction transaction) {
			transactionRepository.save(transaction);
		}
		
		public Optional<Transaction> getTransactionById(UUID id) {
			return transactionRepository.findById(id);
		}
		
}
