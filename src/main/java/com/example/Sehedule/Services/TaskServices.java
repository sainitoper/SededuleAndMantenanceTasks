package com.example.Sehedule.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Sehedule.CustomException.StatusTpyeNotNullException;
import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Entity.Task;
import com.example.Sehedule.Repo.AreaRepo;
import com.example.Sehedule.Repo.TaskRepo;

@Service
public class TaskServices {
	@Autowired
	private TaskRepo taskRepo; 
	
	@Autowired
	private AreaRepo areaRepo;
	
	
	@Autowired 
	private ModelMapper mapper; 
	
	
	private TaskDto TaskToDto(Task task)
	{
		return mapper.map(task, TaskDto.class);
	}
	
	private Task DtoToTask(TaskDto taskDto)
	{
		return mapper.map(taskDto, Task.class);
	}
	
	
	
	public TaskDto sehedualeTask(TaskDto taskDto,long id)
	{
		Area area= areaRepo.findById(id).orElseThrow();
	 Task task = DtoToTask(taskDto);
	 task.setArea(area);
	 if(task.getTasktype()==null)
	 {
		
			
				throw new StatusTpyeNotNullException("Status must not be null");
			
	 }
	 Task savedTask = taskRepo.save(task);
	 return TaskToDto(savedTask);
	}
       
	
	
	public String updateStatus(long id,String status)
	{
		Task t= taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
		t.setStatus(status);
		taskRepo.save(t);
		return "update";
	}
	public List<TaskDto> findWeeklyTasks(LocalDate start, LocalDate end) {
	    List<Task> tasks = taskRepo.findWeeklytask(start, end);
	    return tasks.stream()
	                .map(this::TaskToDto)
	                .collect(Collectors.toList());
	}


	
	public Map<String, Long> getWeekklySummary(LocalDate start,LocalDate end)
	{
		List<Object[]> statsList = taskRepo.getWeeklytasltask(start, end);
		return statsList.stream().collect(Collectors.toMap(s->(String)s[0], s->(Long)s[1]));
	}
	
	public List<TaskDto> GetAllTaskDetails()
	{
		List<Task> tasks= taskRepo.findAll();	
		return tasks.stream().map(this::TaskToDto).collect(Collectors.toList());	
	}
	public Page<TaskDto> GetAllTaskDetailsBypagingAndSorting(Pageable pageable)
	{
		Page<Task> tasks= taskRepo.findAll(pageable);	
		return tasks.map(this::TaskToDto);
	}
	public TaskDto GetTaskDetaisById(long id)
	{
		Task task = taskRepo.findById(id).orElseThrow();
		return TaskToDto(task);
	}

	
	
	
	public void update(TaskDto taskDto,long id)
	{
taskDto.setId(id);
		
	Task task= DtoToTask(taskDto);
		
		taskRepo.save(task);
	}
	

	
	
}
