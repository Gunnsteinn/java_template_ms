package com.template.app.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating that a user with the given email exists in the database.
 */
@Constraint(validatedBy = ExistsByEmailValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByEmail {

    /**
     * The default validation message.
     */
    String message() default "User with this email exists in the database: ${validatedValue}";

    /**
     * Allows the specification of validation groups, to which this constraint belongs.
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint.
     */
    Class<? extends Payload>[] payload() default {};
}
