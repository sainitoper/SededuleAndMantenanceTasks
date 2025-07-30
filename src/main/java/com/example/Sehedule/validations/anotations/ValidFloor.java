package com.example.Sehedule.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.Sehedule.validations.logic.ValidFloorValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidFloorValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFloor {
	String message() default "Floor must be like this-1st,2nd,3rd,4th--e.t.c";
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
