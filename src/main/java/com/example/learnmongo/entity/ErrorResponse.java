package com.example.learnmongo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter @NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;

    public ErrorResponse(int statusCode, HttpStatus status, String message) {
        this.statusCode = statusCode;
        this.httpStatus = status;
        this.message = message;
    }
}
