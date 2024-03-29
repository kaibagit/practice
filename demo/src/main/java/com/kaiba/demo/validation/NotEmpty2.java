package com.kaiba.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by luliru on 2017/6/6.
 */
@NotNull
@Size(min = 1)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
//@Constraint(validatedBy = {NotEmptyValidator2.class})
public @interface NotEmpty2 {
    String message() default "this string may be empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        NotEmpty2[] value();
    }
}
