package com.karma.util.anotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LettersValidator  extends StringPatternValidator implements ConstraintValidator<OnlyLetters, String> {

    public LettersValidator(){
        super("([\\p{L}\\s]+)");
    }
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return this.isValid(value);
    }
}
