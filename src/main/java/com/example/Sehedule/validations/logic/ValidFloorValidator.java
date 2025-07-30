package com.example.Sehedule.validations.logic;

import com.example.Sehedule.validations.anotations.ValidFloor;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFloorValidator implements ConstraintValidator<ValidFloor, String>{
@Override
public boolean isValid(String floor,ConstraintValidatorContext context)
{
	if(floor == null)return false;
	
	return floor.matches("^\\d{1,2}(st|nd|rd|th)$");
}
}
