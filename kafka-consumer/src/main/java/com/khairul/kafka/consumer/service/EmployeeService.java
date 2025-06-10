package com.khairul.kafka.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khairul.kafka.consumer.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public EmployeeService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void store(Employee employee) {
        try {
            String employeeJson = objectMapper.writeValueAsString(employee);
            redisTemplate.opsForValue().set(employee.getName(), employeeJson);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to serialize employee", e);
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*");

        for (String key : keys) {
            Employee employee = getEmployeeByName(key);
            if (employee != null) {
                employees.add(employee);
            }
        }

        return employees;
    }

    public Employee getEmployeeByName(String name) {
        String employeeJson = redisTemplate.opsForValue().get(name);
        
        if (employeeJson == null) {
            LOGGER.warn("Employee with name {} not found in Redis", name);
            return null;
        }
        
        try {
            return objectMapper.readValue(employeeJson, Employee.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to deserialize employee JSON: {}", employeeJson, e);
            return null;
        }
    }
}