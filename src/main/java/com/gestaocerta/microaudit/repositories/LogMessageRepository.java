package com.gestaocerta.microaudit.repositories;

import com.gestaocerta.microaudit.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {
    List<LogMessage> findAllByType(String type);
}
