package br.com.desafio.backvotos.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@Configuration
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue.resultado", true);
    }

}
