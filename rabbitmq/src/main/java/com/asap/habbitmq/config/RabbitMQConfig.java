package com.asap.habbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("transactions-exchange");
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactions-queue");
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue transactionQueue) {
        return BindingBuilder.bind(transactionQueue).to(directExchange).with("transactions-routing-key");
    }
}

