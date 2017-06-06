package com.kaiba.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by luliru on 2017/6/2.
 */
public class AgeValidator implements ConstraintValidator<Age, Integer> {

    @Override
    public void initialize(Age age) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(integer == null || integer < 0 || integer > 150){
            return false;
        }
        return true;
    }
}
