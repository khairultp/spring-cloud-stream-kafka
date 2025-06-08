package com.khairul.kafka.consumer.controller;

import com.khairul.kafka.consumer.model.LogEntry;
import com.khairul.kafka.consumer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<LogEntry> getLogs() {
        return logService.getLogs();
    }

    @GetMapping("/latest")
    public List<LogEntry> getLatestLogs(@RequestParam(defaultValue = "100") int count) {
        return logService.getLatestLogs(count);
    }
}