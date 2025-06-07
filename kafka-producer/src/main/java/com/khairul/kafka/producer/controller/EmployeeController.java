package com.khairul.kafka.producer.controller;

import com.khairul.kafka.producer.model.Employee;
import com.khairul.kafka.producer.service.EmployeeProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeProducer employeeProducer;

    public EmployeeController(EmployeeProducer employeeProducer) {
        this.employeeProducer = employeeProducer;
    }

    @PostMapping("/employees")
    public String save(@RequestBody Employee employee) {
        employeeProducer.send(employee);
        return "Employee sent: " + employee.getName();
    }
}
