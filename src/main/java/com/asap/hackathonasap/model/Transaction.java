package com.asap.hackathonasap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {

	@Id
	private String id;
	private String personId;
	private String transactionDate;
	private double amount;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
