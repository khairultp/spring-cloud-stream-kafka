package com.khairul.kafka.consumer.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
