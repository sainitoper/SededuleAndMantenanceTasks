package com.example.Sehedule;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.validations.logic.ValidFloorValidator;
import com.example.Sehedule.validations.logic.ValidWingValidator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AreaDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenValidAreaDto_thenNoConstraintViolations() {
        AreaDto area = new AreaDto();
        area.setId(1);
        area.setFloor("1st");
        area.setWing("E");   

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(area);

       
        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

        assertTrue(violations.isEmpty());
    }

    @Test
    void whenInvalidId_thenConstraintViolation() {
        AreaDto area = new AreaDto();
        area.setId(-1); 
        area.setFloor("ValidFloorValue");
        area.setWing("ValidWingValue");

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(area);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("id") &&
                               v.getMessage().contains("greater then 0")));
    }
    
    @Test
    void whenInvalidFloor() {
        AreaDto area = new AreaDto();
        area.setId(1); 
        area.setFloor("ValidFloorValue");
        area.setWing("ValidWingValue");

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(area);
        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("floor")));
    }
    
    @Test
    void whenInvalidWing() {
        AreaDto area = new AreaDto();
        area.setId(1); 
        area.setFloor("2nd");
        area.setWing("ValidWingValue");

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(area);
        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("wing")));
    }
    @Test
    void testWingIsNull() {
        ValidWingValidator validator = new ValidWingValidator();
        boolean result = validator.isValid(null, null);  
        assertFalse(result);  
    }
    @Test
    void testfloorIsNull() {
        ValidFloorValidator validator = new ValidFloorValidator();
        boolean result = validator.isValid(null, null);  
        assertFalse(result); 
    }



}
