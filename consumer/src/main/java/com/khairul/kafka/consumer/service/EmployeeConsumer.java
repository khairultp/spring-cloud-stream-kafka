package com.khairul.kafka.consumer.service;

import com.khairul.kafka.consumer.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EmployeeConsumer {
    @Bean
    public Consumer<Employee> input() {
        return employee -> System.out.println("Received employee: " + employee.getName() + " from department " + employee.getDepartment());
    }
}
