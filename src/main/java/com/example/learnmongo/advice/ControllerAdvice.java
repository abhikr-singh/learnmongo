package com.example.learnmongo.advice;

import com.example.learnmongo.api.CustomerController;
import com.example.learnmongo.api.SizeInBytesController;
import com.example.learnmongo.entity.ErrorResponse;
import com.example.learnmongo.exceptions.BadRequestException;
import com.example.learnmongo.exceptions.ResourceAlreadyExists;
import com.example.learnmongo.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(assignableTypes = {CustomerController.class, SizeInBytesController.class})
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
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

    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExists(ResourceAlreadyExists e) {
        return new ErrorResponse(HttpServletResponse.SC_CONFLICT, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExists(BadRequestException e) {
        return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGenericException(Exception e) {
        return new ErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());

    }
}
