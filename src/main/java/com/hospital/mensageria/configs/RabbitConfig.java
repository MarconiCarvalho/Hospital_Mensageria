package com.hospital.mensageria.configs;

import com.hospital.mensageria.service.CallHistoryService;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue filaPriority() {
        return new Queue("fila_prioridade", true);
    }

    @Bean
    public Queue filaRemove() {
        return new Queue("fila_remocao", true);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapterPriority, MessageListenerAdapter listenerAdapterRemove) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        container.setQueueNames("fila_prioridade", "fila_remocao");
        container.setMessageListener(listenerAdapterPriority);
        container.setMessageListener(listenerAdapterRemove);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapterPriority(CallHistoryService callHistoryService) {
        return new MessageListenerAdapter(callHistoryService, "processarChamada");
    }

    @Bean
    public MessageListenerAdapter listenerAdapterRemove(CallHistoryService callHistoryService) {
        return new MessageListenerAdapter(callHistoryService, "processarRemocao");
    }
}
