package com.example.learnmongo.advice;

import com.example.learnmongo.entity.ErrorResponse;
import com.example.learnmongo.exceptions.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBeanValidation(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError field : fieldErrors) {
            errorMessage.append(field.getField())
                    .append(": ")
                    .append(field.getDefaultMessage())
                    .append(", ");
        }
        errorMessage.setCharAt(errorMessage.length() - 2, '.');
        return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                HttpStatus.BAD_REQUEST, errorMessage.toString().trim());
    }
}
