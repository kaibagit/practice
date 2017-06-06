package com.kaiba.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by luliru on 2017/6/5.
 */
public class PatternOfStringValidator implements ConstraintValidator<PatternOfString, String> {

    private String letterIn;
    public void initialize(PatternOfString parameters) {
        this.letterIn=parameters.mustContainLetter();
    }
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string != null && string.contains(letterIn))
            return true;
        return false;
    }
}
