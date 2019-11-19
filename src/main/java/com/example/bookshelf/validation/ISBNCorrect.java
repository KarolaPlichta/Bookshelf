package com.example.bookshelf.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ISBNNumberValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBNCorrect {

    String message() default "Invalid ISBN number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
