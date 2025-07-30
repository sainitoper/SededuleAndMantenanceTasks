package com.example.Sehedule.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.Sehedule.validations.logic.ValidWingValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidWingValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWing {
	String message() default "Wing name must be character";
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
