package com.kaiba.demo.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * Created by luliru on 2017/6/6.
 */
public interface MyGroup {

    public static void main(String[] args){
        User user = new User();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user,MyGroup.class);
//        Set<ConstraintViolation<User>> set = validator.validate(user);
        for (ConstraintViolation<User> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}

class User {
    @NotEmpty(message = "firstname may be empty")
    private String firstname;

    @NotEmpty(message = "middlename may be empty", groups = Default.class)
    private String middlename;

    @NotEmpty(message = "lastname may be empty",groups = MyGroup.class)
    private String lastname;
}
