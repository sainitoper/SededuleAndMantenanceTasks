package com.example.Sehedule.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


import com.example.Sehedule.Controller.AreaControler;
import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Services.AreaServices;
import org.springframework.http.MediaType;


@WebMvcTest(AreaControler.class)
public class AreaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AreaServices areaServices;
	
	@Test
	void testgetAll() throws Exception
	{
		AreaDto areaDto = new AreaDto();
		areaDto.setId(1L);
		areaDto.setFloor("15th");
		areaDto.setWing("U");
		
		when(areaServices.GetAllAreaDetails()).thenReturn(List.of(areaDto));
		
		mockMvc.perform(get("/area/example"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].floor").value("15th"))
		.andExpect(jsonPath("$[0].wing").value("U"));
		
	}
	
	@Test
	void testInsert()throws  Exception
	{
		AreaDto areaDto = new AreaDto();
		areaDto.setId(1L);
		areaDto.setFloor("15th");
		areaDto.setWing("U");
		
		doNothing().when(areaServices).SaveDetails(any());
		
		mockMvc.perform(post("/area")
		        .contentType(MediaType.APPLICATION_JSON)  
		        .content("{\"id\":1,\"floor\":\"15th\",\"wing\":\"Y\"}"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Done"));

	}
	
	
}
