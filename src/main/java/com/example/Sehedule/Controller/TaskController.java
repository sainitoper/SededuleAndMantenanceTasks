package com.example.Sehedule.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Entity.Task;
import com.example.Sehedule.Services.CsvServices;
import com.example.Sehedule.Services.TaskServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskServices taskServices;
	
	@Autowired
private CsvServices csvServices;
	
	
	
	
	
	@GetMapping("/getAlltask")
	public List<TaskDto> getAll()
	{
		return taskServices.GetAllTaskDetails();
		
	}
	
	@GetMapping("id/{myid}")
	public TaskDto getByTask(@PathVariable long myid)
	{
		return taskServices.GetTaskDetaisById(myid);
	}
	
	@PostMapping("Insert/{myid}")
	public String Insert(@Valid @RequestBody TaskDto taskDto,@PathVariable long myid)
	{
		 taskServices.sehedualeTask(taskDto,myid);
		 return "Done insert task";
	}
	
    @PutMapping("/{myid}/status")
    public String update( @Valid @PathVariable long myid,@RequestParam String status)
    {
    	taskServices.updateStatus(myid, status);
    	return "update";
    }
	
	
	@GetMapping("/weekly-task")
	public List<TaskDto> getAllWeeklytask(@RequestParam("start") String s,@RequestParam("end") String e)
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
