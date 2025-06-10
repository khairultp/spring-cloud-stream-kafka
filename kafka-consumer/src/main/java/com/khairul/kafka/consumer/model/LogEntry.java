package com.khairul.kafka.consumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
public class LogEntry {
    private String id;
    private ZonedDateTime timestamp;
    private String message;
    private String employeeName;
    private String department;

    public LogEntry() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public LogEntry(String message, String employeeName, String department) {
        this();
        this.message = message;
        this.employeeName = employeeName;
        this.department = department;
    }
}
