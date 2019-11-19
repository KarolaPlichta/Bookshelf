package com.example.bookshelf.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ISBNNumberValidator implements ConstraintValidator<ISBNCorrect, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        if (s.matches("[0-9]+") && (s.length() == 13 || s.length() == 10)) {
            return validateISBN(s);
        }
        return false;
    }

    private boolean validateISBN(String s) {
        var multiplyBy = 1;
        var sum = 0;
        var controlDigit = Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
        for (char digit : s.toCharArray()) {
            int parsedDigit = Integer.parseInt(String.valueOf(digit));
            sum += parsedDigit * multiplyBy;
            multiplyBy = multiplyBy == 1 ? 3 : 1;
        }

        return controlDigit == (10 - sum % 10) % 10;
    }
}
