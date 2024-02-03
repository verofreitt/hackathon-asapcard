package com.asap.hackathonasap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {

	@Id
	private String id;
	private String personId;
	private String transactionDate;
	private double amount;
	private char status;
	
	@Column(name = "STATUS", nullable = false)
    private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
<<<<<<< HEAD
	public void setConciliationStatus(String string) {
		// TODO Auto-generated method stub
		
=======
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
>>>>>>> efd6facf409055cad77ed76045c0364ed4c8580a
	}
	
	
}
