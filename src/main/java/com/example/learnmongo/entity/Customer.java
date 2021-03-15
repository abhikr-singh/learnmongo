package com.example.learnmongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter @ToString
public class Customer {
    @Id
    private String username;
    private String firstName;
    private String lastName;
}
