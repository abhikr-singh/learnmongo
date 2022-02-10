package com.example.learnmongo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Customer {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @CreatedDate
    @Indexed
    private Date createdOn;
    @LastModifiedDate
    private Date modifiedOn;
}
