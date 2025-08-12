package com.example.Sehedule.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.multipart.MultipartFile;


import com.example.Sehedule.Controller.CsvController;
import com.example.Sehedule.Services.CsvServices;

@WebMvcTest(CsvController.class)
public class CsvControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CsvServices csvServices;
	
	@Test
	void testCsvfileuploadtodb() throws Exception
	{
		MockMultipartFile multipartFile = new MockMultipartFile
				("file","area.csv","text/csv","id,floor,wing".getBytes());
		
		 when(csvServices.Csvupload(any(InputStream.class))).thenReturn("Csv file upload");

		
		mockMvc.perform(multipart("/csvcontro/csv-file").file(multipartFile))
		.andExpect(status().isOk())
		.andExpect(content().string("Csv file upload"));
	}

	@Test
	void testCsvfileuploadtowithout() throws Exception
	{
		MockMultipartFile multipartFile = new MockMultipartFile
				("file","area.csv","text/csv",new byte[0]);
		
		

		
		mockMvc.perform(multipart("/csvcontro/csv-file").file(multipartFile))
		.andExpect(status().isOk())
		.andExpect(content().string("file not found"));
	}
	

	@Test
	void testCsvfileuploadto_Exception() throws Exception
	{
		MockMultipartFile multipartFile = new MockMultipartFile
				("file","area.csv","text/csv","id,floor,wing".getBytes());
		
		 when(csvServices.Csvupload(any(InputStream.class)))
	        .thenThrow(new RuntimeException("Something went wrong"));

		
		mockMvc.perform(multipart("/csvcontro/csv-file").file(multipartFile))
		.andExpect(status().isOk())
		.andExpect(content().string("Something went wrong"));
	}
	
	@Test
	void testCsvMapping() throws Exception
	{
		MockMultipartFile multipartFile = new MockMultipartFile
				("file","task.csv","text/csv","mapping,data".getBytes());
		
		 when(csvServices.Csvupload(any(InputStream.class))).thenReturn("Done Csv Mapping");

		
		mockMvc.perform(multipart("/csvcontro/csv-mapping").file(multipartFile))
		.andExpect(status().isOk())
		.andExpect(content().string("Done Csv Mapping"));
	}
	 @Test
	    void testCsvMapping_Exception() throws Exception {
	        MockMultipartFile file = new MockMultipartFile(
	            "file", "mapping.csv", "text/csv", "mapping,data".getBytes());

	        when(csvServices.CsvMapping(any(InputStream.class)))
	        .thenThrow(new RuntimeException("Mapping error"));

	        mockMvc.perform(multipart("/csvcontro/csv-mapping")
	                .file(file))
	            .andExpect(status().isOk())
	            .andExpect(content().string("Mapping error"));
	    }
	 @Test
	 void testCsvfileuploadto_InvalidExtension() throws Exception {
	     MockMultipartFile multipartFile = new MockMultipartFile(
	         "file", "area.txt", "text/plain", "id,floor,wing".getBytes());

	     mockMvc.perform(multipart("/csvcontro/csv-file").file(multipartFile))
	         .andExpect(status().isOk())
	         .andExpect(content().string("file not found"));
	 }

	 
}
