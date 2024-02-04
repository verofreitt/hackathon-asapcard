package com.asap.ingestaodb;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.asap.ingestaodb.model.MensagemConciliacao;
import com.asap.ingestaodb.model.MensagemTransacao;
import com.asap.ingestaodb.model.Person;
import com.asap.ingestaodb.model.Transaction;
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

public class MensageriaConciliacoes {
	private final static String QUEUE_NAME = "conciliacoes";
	private final static String HOST = "localhost";
	private final static String USER = "root";
	private final static String PASS = "root";
	private final static int PORT = 5672;
	private final TransactionService transactionService;
	
	public MensageriaConciliacoes(TransactionService transactionService) {
        this.transactionService = transactionService;
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
		System.out.println("Consumindo Conciliacoes...");
		
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
                MensagemConciliacao mensagem = g.fromJson(message, MensagemConciliacao.class);
                
                //puxa transacao
                Optional<Transaction> t = transactionService.getTransactionById(mensagem.id);
                
                //inserir transacao
                if(t.isPresent()) {
                	t.get().setStatus(mensagem.status);
                	transactionService.addNewTransaction(t.get());
                }
                
                System.out.println("Consumed: " + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);

//		channel.close();
//		connection.close();
	}
}
