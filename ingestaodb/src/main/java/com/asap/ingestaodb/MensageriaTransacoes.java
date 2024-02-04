package com.asap.ingestaodb;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.asap.ingestaodb.model.MensagemTransacao;
import com.asap.ingestaodb.model.Person;
import com.asap.ingestaodb.model.Transaction;
import com.asap.ingestaodb.repository.PersonRepository;
import com.asap.ingestaodb.service.InstallmentService;
import com.asap.ingestaodb.service.PersonService;
import com.asap.ingestaodb.service.TransactionService;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MensageriaTransacoes {
	private final static String QUEUE_NAME = "transacoes";
	private final static String HOST = "localhost";
	private final static String USER = "root";
	private final static String PASS = "root";
	private final static int PORT = 5672;
	private final PersonService personService;
	private final TransactionService transactionService;
	private final InstallmentService installmentService;
	
	public MensageriaTransacoes(PersonService personService, TransactionService transactionService,
			InstallmentService installmentService) {
        this.personService = personService;
        this.transactionService = transactionService;
        this.installmentService = installmentService;
    }
	
	public void consume() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USER);
		factory.setPassword(PASS);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		Gson g = new Gson();  
		System.out.println("Consumindo Transacoes...");
		
		DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {

            	//pela string da mensagem do rabbitmq
                String message = new String(body, "UTF-8");
                
                //transforma string em json e monta a classe
                MensagemTransacao mensagem = g.fromJson(message, MensagemTransacao.class);
//                System.out.println(mensagem);
                
                //cria a pessoa para usar na criacao das tabelas e auxiliares
                Person p = new Person();
                p.setId(mensagem.document);
                p.setAge(mensagem.age);
                p.setName(mensagem.name);
                
//                System.out.println(p);
                
                //inserir pessoa
                if(personService.personExists(mensagem.document)) {
                	System.out.println("Pessoa já cadastrada");
                }else {                  
                    personService.addNewPerson(p);
                }
                
                //inserir transacao
                Transaction t = new Transaction();
                t.setId(mensagem.id);
                t.setPerson(p);
                t.setAmount(mensagem.value);
                t.setTransactionDate(mensagem.date);
                t.setStatus("P");
                
                transactionService.addNewTransaction(t);
                
                //inserir loop de parcelas
                installmentService.addInstallments(t, mensagem.num, t.getAmount());
                
                System.out.println("Consumed: " + message);
                
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);

//		channel.close();
//		connection.close();
	}
}
