package com.kaiba.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by luliru on 2017/6/5.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PatternOfStringValidator.class)
public @interface PatternOfString {

    String mustContainLetter();
    String message() default "this pattern may not be right";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        PatternOfString[] value();
    }
}
