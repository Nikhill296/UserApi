package com.UserApi.Service.CustomAnno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = isPrimeNumValidator.class)
public @interface isPrimeNum {
	
	String message() default "It Shoud be a prime number";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
