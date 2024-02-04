package com.asap.hackathonasap;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender {

    private static String QUEUE_NAME;
    private final static String HOST = "localhost";
    private final static String USER = "root";
    private final static String PASS = "root";
    private final static int PORT = 5672;
    
    public RabbitMQSender(String QUEUE_NAME) {
    	this.QUEUE_NAME = QUEUE_NAME;
    }

    public void enviar(String message) throws IOException, TimeoutException {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USER);
        factory.setPassword(PASS);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(this.QUEUE_NAME, false, false, false, null);

            channel.basicPublish("", this.QUEUE_NAME, null, message.getBytes());
            System.out.println("Produced: " + message);
        
        channel.close();
        connection.close();
    }
    
}