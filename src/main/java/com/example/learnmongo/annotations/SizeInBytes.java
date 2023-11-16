package com.example.learnmongo.annotations;

import com.example.learnmongo.validator.SizeInBytesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SizeInBytesValidator.class})
@Documented
public @interface SizeInBytes {

    String message() default "Size is greater than the required size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value();

}
