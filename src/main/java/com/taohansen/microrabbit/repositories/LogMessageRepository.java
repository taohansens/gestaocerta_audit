package com.taohansen.microrabbit.repositories;

import com.taohansen.microrabbit.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {
}
