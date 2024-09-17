package com.gestaocerta.microaudit.services;

import com.gestaocerta.microaudit.config.RabbitMQConfig;
import com.gestaocerta.microaudit.entities.LogMessage;
import com.gestaocerta.microaudit.repositories.LogMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<LogMessage> getAllAuditMessages() {
        return logMessageRepository.findAllByType("AUDIT");
    }

    public List<LogMessage> getAllLogMessages() {
        return logMessageRepository.findAllByType("LOG");
    }
}