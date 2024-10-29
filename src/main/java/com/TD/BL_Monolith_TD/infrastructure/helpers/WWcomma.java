package com.TD.BL_Monolith_TD.infrastructure.helpers;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = WWcomaValidatorString.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface WWcomma {

    String message() default "The letters must be followed by comma";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] words() default{};
}
