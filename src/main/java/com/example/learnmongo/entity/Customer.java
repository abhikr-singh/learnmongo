package com.example.learnmongo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document
@Data
@Builder
public class Customer {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank
    @Max(3)
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
