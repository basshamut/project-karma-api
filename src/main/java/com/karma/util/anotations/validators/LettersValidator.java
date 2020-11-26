package com.karma.util.anotations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LettersValidator  extends StringPatternValidator implements ConstraintValidator<OnlyLetters, String> {

    public LettersValidator(){
        super("([A-Za-z\\s]+)");
    }
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return this.isValid(value);
    }
}
