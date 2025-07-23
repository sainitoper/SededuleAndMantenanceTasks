package com.example.Sehedule.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Task sehedualeTask(Task task,long id)
	{
		Area area = areaRepo.findById(id).orElseThrow();
		task.setArea(area);
		return taskRepo.save(task);
	}
       
	
	
	public String updateStatus(long id,String status)
	{
		Task t= taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
		t.setStatus(status);
		taskRepo.save(t);
		return "update";
	}
	public List<Task> findWeeklyTasks(LocalDate start, LocalDate end) {
	    return taskRepo.findWeeklytask(start, end);
	}

	
	public Map<String, Long> getWeekklySummary(LocalDate start,LocalDate end)
	{
		List<Object[]> statsList = taskRepo.getWeeklytasltask(start, end);
		return statsList.stream().collect(Collectors.toMap(s->(String)s[0], s->(Long)s[1]));
	}
	
	public List<Task> GetAllTaskDetails()
	{
		return taskRepo.findAll();	
	}
	
	public Task GetTaskDetaisById(long id)
	{
		return taskRepo.findById(id).orElseThrow(null);
	}

	
	
	
	public void update(Task task,long id)
	{
		task.setId(id);
		taskRepo.save(task);
	}
	
	public void delete(long id)
	{
		taskRepo.deleteById(id);
	}
	
	
}
