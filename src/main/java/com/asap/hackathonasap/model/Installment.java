package com.asap.hackathonasap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Installment {

	@Id
	private String id;
	private String transactionId;
	private int installmentNumber;
	private double value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public int getInstallmentNumber() {
		return installmentNumber;
	}
	public void setInstallmentNumber(int installmentNumber) {
		this.installmentNumber = installmentNumber;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
