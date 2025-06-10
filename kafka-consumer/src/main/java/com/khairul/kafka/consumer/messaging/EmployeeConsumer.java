package com.khairul.kafka.consumer.messaging;

import com.khairul.kafka.consumer.model.Employee;
import com.khairul.kafka.consumer.model.LogEntry;
import com.khairul.kafka.consumer.service.EmployeeService;
import com.khairul.kafka.consumer.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EmployeeConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    private final LogService logService;
    private final EmployeeService employeeService;

    public EmployeeConsumer(LogService logService, EmployeeService employeeService) {
        this.logService = logService;
        this.employeeService = employeeService;
    }

    @Bean
    public Consumer<Employee> input() {
        return employee -> {
            String message = "Received employee: " + employee.getName() + " from department " + employee.getDepartment();
            LOGGER.info("Received message: {}", message);

            employeeService.store(employee);

            LogEntry logEntry = new LogEntry(message, employee.getName(), employee.getDepartment());
            logService.addLog(logEntry);
        };
    }
}