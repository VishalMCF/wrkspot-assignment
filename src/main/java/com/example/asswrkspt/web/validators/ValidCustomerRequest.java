package com.example.asswrkspt.web.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
@Constraint(validatedBy = CustomerCreateRequestValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface ValidCustomerRequest {

    String message() default "The input is not correct";

    Class[] groups() default {};

    Class[] payload() default {};

}
