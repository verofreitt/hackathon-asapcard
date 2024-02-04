package com.asap.ingestaodb.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MensagemTransacao {
	@JsonProperty("id")
	public UUID id;

	@JsonProperty("date")
	public Timestamp date;

	@JsonProperty("document")
	public String document;

	@JsonProperty("name")
	public String name;

	@JsonProperty("age")
	public int age;

	@JsonProperty("value")
	public double value;

	@JsonProperty("num")
	public int num;

	@Override
	public String toString() {
		return "MensagemTransacao [id=" + id + ", date=" + date + ", document=" + document + ", name=" + name + ", age="
				+ age + ", value=" + value + ", num=" + num + "]";
	}

}
