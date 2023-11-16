package com.example.learnmongo.advice;


import com.example.learnmongo.api.CustomerReactiveController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {CustomerReactiveController.class})
@Slf4j
public class ControllerReactiveAdvice {
}
