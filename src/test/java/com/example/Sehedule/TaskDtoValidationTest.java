package com.example.Sehedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TaskDtoValidationTest {

private static Validator validator;	


	@BeforeAll
	static public void setup()
	{
		ValidatorFactory vactory = Validation.buildDefaultValidatorFactory();
		validator = vactory.getValidator();
		}
	
	
	@Test
	public void checkvalidtasks()
	{
		TaskDto task = new TaskDto();
		task.setId(1L);
		task.setTasktype("Cleaning");
		task.setSehduleDate(LocalDate.parse("2025-09-21"));
		task.setStatus("PENDING");
		
AreaDto area = new AreaDto();
		
		task.setAreaDto(area);
		
		Set<ConstraintViolation<TaskDto>> volidations = validator.validate(task);
		
		volidations.forEach(v->System.out.println(v.getPropertyPath()+": "+v.getMessage()));
		assertTrue(volidations.isEmpty());
	
	}
	
	@Test
	public void checkINValidtasks()
	{
		TaskDto task = new TaskDto();
		task.setId(-1L);
		task.setTasktype("Cleaning");
		task.setSehduleDate(LocalDate.parse("2025-04-13"));
		task.setStatus("Complete");
	AreaDto area = new AreaDto();
task.setAreaDto(area);
		Set<ConstraintViolation<TaskDto>> violations  = validator.validate(task);
	
		assertFalse(violations .isEmpty());
		assertTrue(violations.stream().allMatch(violation -> 
        violation.getPropertyPath().toString().equals("id") &&
        violation.getMessage().contains("greater then 0")));
		
	}
	
}
