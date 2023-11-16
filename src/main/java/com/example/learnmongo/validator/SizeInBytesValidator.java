package com.example.learnmongo.validator;

import com.example.learnmongo.annotations.SizeInBytes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeInBytesValidator implements ConstraintValidator<SizeInBytes, String> {

    int validValue;

    @Override
    public void initialize(SizeInBytes constraintAnnotation) {
        this.validValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.getBytes().length <= validValue;
    }
}
