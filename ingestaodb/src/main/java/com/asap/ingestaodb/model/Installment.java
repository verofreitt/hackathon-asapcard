package com.asap.ingestaodb.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private UUID id;

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_ID")
    private Transaction transaction;
    
    @Column(name = "INSTALLMENT_NUMBER", nullable = false)
    private Integer installmentNumber;// numero de parcelas

    @Column(name = "VALUE", nullable = false)
    private double value;// valor total dividino por numero de parcelas

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double installmentValue) {
		this.value = installmentValue;
	}


	
	
	
	
}
