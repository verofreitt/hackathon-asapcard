package com.asap.ingestaodb.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@Column(name = "ID")
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "PERSON_ID")
	private Person person;//id da tabela person
	
	@Column(name = "TRANSACTION_DATE")
	private Timestamp transactionDate;//vem do arquivo
	
	@Column(name = "AMOUNT")
	private double amount;// valor q vem do arquivo
	
	@Column(name = "STATUS", nullable = false, length=1, columnDefinition="CHAR")
	@ColumnDefault("'P'")// faz com que ao adicionar a coluna todas estejam com valor "P" = Pendente
    private String status = "'P'";

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
