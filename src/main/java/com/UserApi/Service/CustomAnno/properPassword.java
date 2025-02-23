package com.UserApi.Service.CustomAnno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = properPasswordCheck.class)
public @interface properPassword {
	String message() default "please provide proper password";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
