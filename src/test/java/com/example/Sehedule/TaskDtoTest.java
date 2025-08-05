package com.example.Sehedule;

import java.time.LocalDate;

import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;


import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.junit.jupiter.api.Assertions.assertSame;
public class TaskDtoTest {
	@Test
	void testgeterandsetters()
	{
		
		TaskDto task = new TaskDto();
		task.setId(1L);
		task.setTasktype("Cleaning");
		task.setSehduleDate(LocalDate.parse("2025-09-21"));
		task.setStatus("PENDING");
		AreaDto area = new AreaDto();
		
		task.setAreaDto(area);
		
		assertEquals(1L,task.getId());
		assertEquals("Cleaning",task.getTasktype());
		assertEquals(LocalDate.parse("2025-09-21"),task.getSehduleDate());
		assertEquals("PENDING",task.getStatus());
		assertSame(area,task.getAreaDto());
	
		
		
		
	}
}
