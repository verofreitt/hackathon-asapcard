package com.asap.ingestaodb.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MensagemConciliacao {
	@JsonProperty("id")
	public UUID id;

	@JsonProperty("date")
	public Timestamp date;

	@JsonProperty("document")
	public String document;

	@JsonProperty("status")
	public String status;

}
