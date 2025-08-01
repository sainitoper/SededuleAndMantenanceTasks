package com.example.Sehedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TaskDtoValidationTest {

private static Validator validator;	

//@BeforeEach
// public void setupEach()
//{
//	System.out.println("this is excute before every test method");
//}
@AfterAll
public static  void setupAfterEach()
{
	System.out.println("this is excute After every test method");
}
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
	
	@Disabled
	@Test
	public void checkINValidtasks()
	{
		System.out.println("This test should be disabled and not run.");
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
	
	@Test
	public void checkValidStatus()
	{
		
		TaskDto task = new TaskDto();
		task.setId(1L);
		task.setTasktype("Cleaning");
		task.setSehduleDate(LocalDate.parse("2025-04-13"));
		task.setStatus(null);
	AreaDto area = new AreaDto();
task.setAreaDto(area);
		Set<ConstraintViolation<TaskDto>> violations  = validator.validate(task);
		violations.forEach(v->System.out.println(v.getPropertyPath()+": "+v.getMessage()));
		assertFalse(violations .isEmpty());
		assertTrue(violations.stream().allMatch(violation -> 
        violation.getPropertyPath().toString().equals("status") ));
		
	}
	@Test
	public void checkValidArea()
	{
		
		TaskDto task = new TaskDto();
		task.setId(1L);
		task.setTasktype("Cleaning");
		task.setSehduleDate(LocalDate.parse("2025-04-13"));
		task.setStatus("PENDING");

task.setAreaDto(null);
		Set<ConstraintViolation<TaskDto>> violations  = validator.validate(task);
		violations.forEach(v->System.out.println(v.getPropertyPath()+": "+v.getMessage()));
		assertFalse(violations .isEmpty());
		assertTrue(violations.stream().allMatch(violation -> 
        violation.getPropertyPath().toString().equals("areaDto") ));
		
	}
	
}
