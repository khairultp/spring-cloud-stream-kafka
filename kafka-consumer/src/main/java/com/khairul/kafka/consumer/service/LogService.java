package com.khairul.kafka.consumer.service;

import com.khairul.kafka.consumer.model.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    private static final int MAX_LOGS = 1000; // Maximum number of logs to keep in memory

    private final ConcurrentLinkedQueue<LogEntry> logs = new ConcurrentLinkedQueue<>();

    public void addLog(LogEntry logEntry) {
        LOGGER.info("Adding log entry: {}", logEntry);
        logs.add(logEntry);
        
        // Remove oldest logs if we exceed the maximum
        while (logs.size() > MAX_LOGS) {
            LOGGER.info("Removing log entry: {}", logs.poll());
        }
    }

    public List<LogEntry> getLogs() {
        List<LogEntry> logList = new ArrayList<>(logs);
        Collections.reverse(logList); // Most recent logs first
        return logList;
    }

    public List<LogEntry> getLatestLogs(int count) {
        List<LogEntry> allLogs = getLogs();
        return allLogs.subList(0, Math.min(count, allLogs.size()));
    }
}