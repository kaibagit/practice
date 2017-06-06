package com.kaiba.demo.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by luliru on 2017/6/6.
 */
public interface Animal {
    @NotEmpty
    String getName();
    @NotEmpty
    String getOwnerName();

    public static void main(String[] args){
        Dog dog = new Dog();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Dog>> set = validator.validate(dog,Animal.class);
        for (ConstraintViolation<Dog> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}

class Dog implements Animal {
    private String name;
    private String ownername;

    private String type;

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return null;
    }
    public String getOwnerName() {
        return null;
    }
    @NotEmpty(message = "type of the dog may be empty")
    public String getType() {
        return type;
    }
}