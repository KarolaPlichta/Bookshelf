package com.example.bookshelf.validation;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ISBNNumberValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    private ISBNNumberValidator isbnNumberValidator = new ISBNNumberValidator();

    @Test
    void shouldPassIfNumberIsCorrect(){
        //GIVEN
        String ISBNNumber = "9783161484100";

        //WHEN
        var result = isbnNumberValidator.isValid(ISBNNumber, constraintValidatorContext);

        //THEN
        assertTrue(result);
    }

    @Test
    void shouldFailedIfNumberIsInCorrect(){
        //GIVEN
        String ISBNNumber = "9783161484111";

        //WHEN
        var result = isbnNumberValidator.isValid(ISBNNumber, constraintValidatorContext);

        //THEN
        assertFalse(result);
    }

    @Test
    void shouldFailedIfISBNContainsLetters(){
        //GIVEN
        String ISBNNumber = "97831614841aa";

        //WHEN
        var result = isbnNumberValidator.isValid(ISBNNumber, constraintValidatorContext);

        //THEN
        assertFalse(result);
    }

    @Test
    void shouldFailedIfISBNSpecialChars(){
        //GIVEN
        String ISBNNumber = "978-3-16-148410-0";

        //WHEN
        var result = isbnNumberValidator.isValid(ISBNNumber, constraintValidatorContext);

        //THEN
        assertFalse(result);
    }
    @Test
    void shouldFailedIfISBNHasInvalidLength(){
        //GIVEN
        String ISBNNumber = "978334543435";

        //WHEN
        var result = isbnNumberValidator.isValid(ISBNNumber, constraintValidatorContext);

        //THEN
        assertFalse(result);
    }
}
