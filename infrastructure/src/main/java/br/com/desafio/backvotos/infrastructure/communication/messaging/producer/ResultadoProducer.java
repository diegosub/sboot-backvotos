package br.com.desafio.backvotos.infrastructure.communication.messaging.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResultadoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String message) {
        log.info("Iniciando envio da mensagem: " + message + " para a fila de resultado.  QUEUE: " + this.queue.getName());
        rabbitTemplate.convertAndSend(this.queue.getName(), message);
        log.info("Mensagem enviada.");
    }
}