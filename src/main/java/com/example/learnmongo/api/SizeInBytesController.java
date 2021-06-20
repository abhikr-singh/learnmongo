package com.example.learnmongo.api;

import com.example.learnmongo.entity.SizeInBytesBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class SizeInBytesController {

    @PostMapping("/validatesize")
    public ResponseEntity<String> validatedSizeInBytes(@Valid @RequestBody SizeInBytesBean sizeInBytesBean) {
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
