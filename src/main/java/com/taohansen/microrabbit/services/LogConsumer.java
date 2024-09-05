package com.taohansen.microrabbit.services;

import com.taohansen.microrabbit.config.RabbitMQConfig;
import com.taohansen.microrabbit.entities.LogMessage;
import com.taohansen.microrabbit.repositories.LogMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogConsumer {
    private final LogMessageRepository logMessageRepository;

    @RabbitListener(queues = RabbitMQConfig.LOG_QUEUE)
    public void handleLogMessage(String message) {
        System.out.println("Received log: " + message);
        LogMessage log = new LogMessage();
        log.setMessage(message);
        log.setType("LOG");
        log.setOrigin("...");
        logMessageRepository.save(log);
    }

    @RabbitListener(queues = RabbitMQConfig.AUDIT_QUEUE)
    public void handleAuditMessage(String message) {
        System.out.println("Received audit: " + message);
        LogMessage log = new LogMessage();
        log.setMessage(message);
        log.setType("AUDIT");
        log.setOrigin("...");
        logMessageRepository.save(log);
    }
}