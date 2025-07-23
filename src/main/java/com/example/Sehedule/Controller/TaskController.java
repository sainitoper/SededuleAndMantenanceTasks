package com.example.Sehedule.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sehedule.Entity.Task;
import com.example.Sehedule.Services.TaskServices;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskServices taskServices;
	
	@GetMapping("/getAlltask")
	public List<Task> getAll()
	{
		return taskServices.GetAllTaskDetails();
		
	}
	
	@GetMapping("id/{myid}")
	public Task getByTask(@PathVariable long myid)
	{
		return taskServices.GetTaskDetaisById(myid);
	}
	
	@PostMapping("Insert/{myid}")
	public String Insert(@RequestBody Task task,@PathVariable long myid)
	{
		 taskServices.sehedualeTask(task,myid);
		 return "Done insert task";
	}
	
    @PutMapping("/{myid}/status")
    public String update(@PathVariable long myid,@RequestParam String status)
    {
    	taskServices.updateStatus(myid, status);
    	return "update";
    }
	
	
	@GetMapping("/weekly-task")
	public List<Task> getAllWeeklytask(@RequestParam("start") String s,@RequestParam("end") String e)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate start = LocalDate.parse(s, formatter);
	        LocalDate end = LocalDate.parse(e, formatter);
		return taskServices.findWeeklyTasks(start, end);
	}
	
	
	@GetMapping("/weekly-summary")
	public Map<String, Long>  getWeeklySummary(@RequestParam("start") String s,@RequestParam("end") String e)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate start = LocalDate.parse(s, formatter);
	        LocalDate end = LocalDate.parse(e, formatter);
	        return taskServices.getWeekklySummary(start, end);
	}

}
