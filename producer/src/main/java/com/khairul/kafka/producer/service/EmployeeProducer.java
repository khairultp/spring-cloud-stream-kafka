package com.khairul.kafka.producer.service;

import com.khairul.kafka.producer.model.Employee;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {
    private final StreamBridge streamBridge;

    public EmployeeProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(Employee employee) {
        var status = streamBridge.send("output-out-0", employee);
        System.out.printf("Status: %s", status);
    }
}
