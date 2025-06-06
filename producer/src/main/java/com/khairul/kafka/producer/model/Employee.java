package com.khairul.kafka.producer.model;

public class Employee {
    private String id;
    private String name;
    private String department;

    // Constructors, getters, and setters
    public Employee(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
