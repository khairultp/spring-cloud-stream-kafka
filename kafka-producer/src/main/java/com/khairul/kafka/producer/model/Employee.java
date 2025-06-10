package com.khairul.kafka.producer.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String id;
    private String name;
    private String department;

}
