package com.khairul.kafka.consumer.service;

import com.khairul.kafka.consumer.model.Employee;
import com.khairul.kafka.consumer.model.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EmployeeConsumer {

    private final LogService logService;

    @Autowired
    public EmployeeConsumer(LogService logService) {
        this.logService = logService;
    }

    @Bean
    public Consumer<Employee> input() {
        return employee -> {
            String message = "Received employee: " + employee.getName() + " from department " + employee.getDepartment();
            System.out.println(message);

            // Create and store log entry
            LogEntry logEntry = new LogEntry(message, employee.getName(), employee.getDepartment());
            logService.addLog(logEntry);
        };
    }
}
