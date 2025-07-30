package com.example.Sehedule.validations.logic;

import com.example.Sehedule.validations.anotations.ValidWing;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidWingValidator implements ConstraintValidator<ValidWing, String>{
@Override
public boolean isValid(String wing,ConstraintValidatorContext context)
{
	if(wing == null)return false;
	
	return wing.matches("^[A-Z]+$");
}
}
