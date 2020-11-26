package com.karma.util.anotations.validators;

import com.karma.util.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LettersValidator.class)
public @interface OnlyLetters {

    String message() default Constants.BAD_REQUEST_INVALID_INPUT_ONLY_LETTERS_MUST_BE_MATCHED;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
