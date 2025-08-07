package com.example.Sehedule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.runtime.ObjectMethods;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Sehedule.Controller.TaskController;
import com.example.Sehedule.CustomException.StatusTpyeNotNullException;
import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Services.CsvServices;
import com.example.Sehedule.Services.TaskServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskController.class)
public class GlobalExceptionHandlerTest {

	
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private TaskServices taskServices;
	
	  @MockBean
	    private CsvServices csvServices; 
	@Test
	void testhandleValidationErrors()throws Exception
	{
		TaskDto taskDto = new TaskDto();
		taskDto.setId(1L);
		
		 mockMvc.perform(post("/task/Insert/{myid}",1L)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(taskDto)))
		 .andExpect(status().isBadRequest())
		 .andExpect(jsonPath("$.areaDto").exists())
		 .andExpect(jsonPath("$.tasktype").exists())
		    .andExpect(jsonPath("$.status").exists()); 
	             

	}
	
	@Test
	void testCustomExceptionHandler() throws Exception {
	    TaskDto taskDto = new TaskDto();
	    taskDto.setId(1L);
	    taskDto.setTasktype("Test Task");
	    taskDto.setStatus("test");
	    taskDto.setAreaDto(new AreaDto());
	    when(taskServices.sehedualeTask(any(TaskDto.class), eq(1L)))
	        .thenThrow(new StatusTpyeNotNullException("Custom status type error"));

	    mockMvc.perform(post("/task/Insert/{myid}", 1L)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(taskDto)))
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$.['error Message']").value("Custom status type error"));
	}

}
