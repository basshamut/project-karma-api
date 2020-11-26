package com.karma.util.anotations.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class StringPatternValidator {

    final String patternToMatch;

    public StringPatternValidator(final String patternToMatch) {
        if(null == patternToMatch) throw new NullPointerException("patternToMatch");
        this.patternToMatch = patternToMatch;
    }

    public boolean isValid(final String stringToCheck) {
        if(stringToCheck == null) {
            return false;
        }
        try {
            return (Pattern.compile(patternToMatch).matcher(stringToCheck).matches());
        } catch (Exception e) {
            return false;
        }
    }
}
