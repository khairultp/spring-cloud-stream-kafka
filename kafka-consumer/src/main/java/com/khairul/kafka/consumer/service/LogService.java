package com.khairul.kafka.consumer.service;

import com.khairul.kafka.consumer.model.LogEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LogService {
    private final ConcurrentLinkedQueue<LogEntry> logs = new ConcurrentLinkedQueue<>();
    private static final int MAX_LOGS = 1000; // Maximum number of logs to keep in memory

    public void addLog(LogEntry logEntry) {
        logs.add(logEntry);
        
        // Remove oldest logs if we exceed the maximum
        while (logs.size() > MAX_LOGS) {
            logs.poll();
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