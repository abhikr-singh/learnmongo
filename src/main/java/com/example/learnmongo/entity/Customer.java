package com.example.learnmongo.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank
    private String username;
    @NotEmpty
    private String firstName;
    private String lastName;
    @CreatedDate
    @Indexed
    private Date createdOn;
    @LastModifiedDate
    private Date modifiedOn;
}
