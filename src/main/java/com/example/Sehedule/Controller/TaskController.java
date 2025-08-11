package com.example.Sehedule.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Services.CsvServices;
import com.example.Sehedule.Services.TaskServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskServices taskServices;
	
	@Autowired
private CsvServices csvServices;
	
	
	
	
	
	 @GetMapping("/getAlltask")
	    @Operation(summary = "Get all tasks", description = "Fetch all tasks")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks")
	    })
	public List<TaskDto> getAll()
	{
		return taskServices.GetAllTaskDetails();
		
	}
	
	 @GetMapping("/getAllByPaging")
	    @Operation(summary = "Get all By paging", description = "Fetch all tasks by paging and sorting")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks by sorting and paging")
	    })
	public Page<TaskDto> getAlltasks(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc")String sortdir
			)
	{
		 Sort sort = sortdir.equalsIgnoreCase("desc")?
				 Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		 Pageable pageable = PageRequest.of(page, size,sort);
		return taskServices.GetAllTaskDetailsBypagingAndSorting(pageable);
		
	}
	 @GetMapping("/id/{myid}")
	    @Operation(summary = "Get task by ID", description = "Fetch task by its ID")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Task found"),
	            @ApiResponse(responseCode = "404", description = "Task not found")
	    })
	public TaskDto getByTask(@PathVariable long myid)
	{
		return taskServices.GetTaskDetaisById(myid);
	}
	
	  @PostMapping("/Insert/{myid}")
	    @Operation(summary = "Insert task", description = "Assign task to Area")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Task created successfully"),
	            @ApiResponse(responseCode = "400", description = "Invalid task data")
	    })
	public String Insert(@Valid @RequestBody TaskDto taskDto,@PathVariable long myid)
	{
		 taskServices.sehedualeTask(taskDto,myid);
		 return "Done insert task";
	}
	
	   @PutMapping("/{myid}/status")
	    @Operation(summary = "Update task status", description = "Update status of a particular task by its ID")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Task status updated successfully"),
	            @ApiResponse(responseCode = "404", description = "Task not found")
	    })
    public String update( @Valid @PathVariable long myid,@RequestParam String status)
    {
    	taskServices.updateStatus(myid, status);
    	return "update";
    }
	
	   @GetMapping("/weekly-task")
	    @Operation(summary = "Get weekly tasks", description = "Get tasks within a date range")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
	            @ApiResponse(responseCode = "400", description = "Invalid date format")
	    })
	public List<TaskDto> getAllWeeklytask(@RequestParam("start") String s,@RequestParam("end") String e)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate start = LocalDate.parse(s, formatter);
	        LocalDate end = LocalDate.parse(e, formatter);
		return taskServices.findWeeklyTasks(start, end);
	}
	
	   @GetMapping("/weekly-summary")
	    @Operation(summary = "Get weekly summary", description = "Get summary of tasks grouped by status within a date range")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Summary retrieved successfully"),
	            @ApiResponse(responseCode = "400", description = "Invalid date format")
	    })
	public Map<String, Long>  getWeeklySummary(@RequestParam("start") String s,@RequestParam("end") String e)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate start = LocalDate.parse(s, formatter);
	        LocalDate end = LocalDate.parse(e, formatter);
	        return taskServices.getWeekklySummary(start, end);
	}

}
