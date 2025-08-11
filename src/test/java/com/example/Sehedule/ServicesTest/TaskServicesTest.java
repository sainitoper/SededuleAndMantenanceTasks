package com.example.Sehedule.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.Sehedule.CustomException.StatusTpyeNotNullException;
import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Entity.Task;
import com.example.Sehedule.Repo.AreaRepo;
import com.example.Sehedule.Repo.TaskRepo;
import com.example.Sehedule.Services.TaskServices;

@ExtendWith(MockitoExtension.class)
public class TaskServicesTest {

	@Mock
	private TaskRepo taskRepo;
	@Mock
	private AreaRepo areaRepo;
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private TaskServices taskServices;
	
	private TaskDto taskDto;
	private Task task;
	private Area area;
	private AreaDto areaDto;
	
	
	@BeforeEach
	void setup()
	{
		taskDto = new TaskDto();
		taskDto.setTasktype("Cleaning");
		taskDto.setStatus("PENDING");

		areaDto = new AreaDto();
	    areaDto.setId(1L);
		task = new Task();
		task.setTasktype("Cleaning");
		task.setStatus("PENDING");

		taskDto.setAreaDto(areaDto);
		
		area = new Area();
		area.setId(1L);
	}
	
	@Test
	void testsehedualeTask()
	{
		when(areaRepo.findById(1L)).thenReturn(Optional.of(area));
		when(mapper.map(taskDto,Task.class)).thenReturn(task);
		when(taskRepo.save(task)).thenReturn(task);
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);
		
		TaskDto tempDto = taskServices.sehedualeTask(taskDto, 1L);
		assertNotNull(tempDto);
		assertEquals("Cleaning",tempDto.getTasktype());
		assertEquals(1L, tempDto.getAreaDto().getId());
		
		verify(areaRepo).findById(1L);

		verify(taskRepo).save(task);
		verify(mapper).map(taskDto, Task.class);
		verify(mapper).map(task, TaskDto.class);

		
		
	}
	
	
	@Test
	void checkwhenStatusNullhandleException()
	{
		task.setTasktype(null);
		taskDto.setTasktype(null);
		when(areaRepo.findById(1L)).thenReturn(Optional.of(area));
		when(mapper.map(taskDto,Task.class)).thenReturn(task);
		
	     assertThrows(StatusTpyeNotNullException.class, 
	    		 ()->{taskServices.sehedualeTask(taskDto, 1L);});
	     
	     verify(areaRepo).findById(1L);
	     verify(taskRepo,never()).save(any());
	}
	
	
	@Test
	void checktestforupdateStatus()
	{
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
		when(taskRepo.save(task)).thenReturn(task);
		assertNotNull(task);
		
		taskServices.updateStatus(1L,"COMPLETE");
		
		
		
		assertEquals("COMPLETE",task.getStatus() );
		verify(taskRepo).findById(1L);
		verify(taskRepo).save(task);
		
	}
	@Test
	void checktestforupdateStatus_whenTaskNotFound_thenThrowException() {
	   
	    when(taskRepo.findById(1L)).thenReturn(Optional.empty());

	   
	    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	        taskServices.updateStatus(1L, "COMPLETE");
	    });

	    assertEquals("Task with ID 1 not found", exception.getMessage());

	 
	    verify(taskRepo).findById(1L);
	    verify(taskRepo, never()).save(any());
	}

	
	@Test
	void checktestfindWeeklyTasks()
	{
		
		LocalDate date =  LocalDate.parse("2025-11-23");
		
		Task task1 = new Task();
		task1.setId(2L);
		task1.setTasktype("Watching");
		Task task2 = new Task();
		task2.setId(3L);
		task2.setTasktype("Cleaning");
		List<Task> tasklList = List.of(task1,task2);
		TaskDto taskDto1 = new TaskDto();
		taskDto1.setId(2L);
		taskDto1.setTasktype("Watching");
		TaskDto taskDto2 = new TaskDto();
		taskDto2.setId(3L);
		taskDto2.setTasktype("Cleaning");
		
		
		
		
		when(taskRepo.findWeeklytask(date, date)).thenReturn(tasklList);
		when(mapper.map(task1, TaskDto.class)).thenReturn(taskDto1);
		when(mapper.map(task2, TaskDto.class)).thenReturn(taskDto2);
		
		List<TaskDto> taskDtos = taskServices.findWeeklyTasks(date, date);
		
		assertNotNull(taskDtos);
		assertEquals(2L, taskDtos.get(0).getId());
		assertEquals("Watching",taskDtos.get(0).getTasktype());
		assertEquals(3L, taskDtos.get(1).getId());
		assertEquals("Cleaning",taskDtos.get(1).getTasktype());
		
		
		verify(taskRepo).findWeeklytask(date, date);
		verify(mapper).map(task1, TaskDto.class);
		verify(mapper).map(task2, TaskDto.class);
		
		
	}
	
	@Test
	void checktestgetWeekklySummary()
	{
		LocalDate start = LocalDate.parse("2025-03-12");
		LocalDate end = LocalDate.parse("2025-03-12");
		
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[] {"Task1",3l});
		list.add(new Object[] {"Task2",5l});
		
		when(taskRepo.getWeeklytasltask(start, end)).thenReturn(list);
		
		Map<String, Long> map = taskServices.getWeekklySummary(start, end);
		
		assertEquals(2, map.size());
		assertEquals(3l, map.get("Task1"));
		assertEquals(5l, map.get("Task2"));
	}
	@Test
	void testGetAllTaskDetails()
	{
		
		List<Task> tasks = List.of(task);
		when(taskRepo.findAll()).thenReturn(tasks);
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);
		
		List<TaskDto> list = taskServices.GetAllTaskDetails();
		
		assertEquals(1, list.size());
		assertEquals("Cleaning", list.get(0).getTasktype());
		verify(taskRepo).findAll();
		
	}
	
	@Test
	void testGetAreaDetaisById()
	{
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);
		
		TaskDto taskDto = taskServices.GetTaskDetaisById(1L);
		
		assertEquals("Cleaning", taskDto.getTasktype());
		assertEquals("PENDING", taskDto.getStatus());
		
		verify(taskRepo).findById(1L);
	}
	@Test
	void testSaveDetails()
	{
		when(mapper.map(taskDto,Task.class)).thenReturn(task);
		
		taskServices.update(taskDto,1L);
		
		verify(taskRepo).save(task);
	}
	

	
}
