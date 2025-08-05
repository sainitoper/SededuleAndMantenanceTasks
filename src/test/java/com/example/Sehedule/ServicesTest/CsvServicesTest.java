package com.example.Sehedule.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.argThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Repo.AreaRepo;
import com.example.Sehedule.Services.CsvServices;

@ExtendWith(MockitoExtension.class)
public class CsvServicesTest {

	
	@Mock
	private AreaRepo areaRepo;
	
	@InjectMocks
	private CsvServices csvServices;
	
	private String Csvareacontent;
	private String Csvmapppingcontent;
	
	
	@BeforeEach
	void setup()
	{
		Csvareacontent = "id,floor,wing\n"+"1,13th,E";
		Csvmapppingcontent="areaId,floor,wing,taskId,taskType,scheduleDate,status\n" +
                "1,First,A,101,Cleaning,2025-08-01,PENDING\n"+"1,First,A,102,Monitoring,2025-08-02,COMPLETE";
	}
	
	@Test
	void testCsvupload()throws IOException
	{
		InputStream inputStream =new ByteArrayInputStream(Csvareacontent.getBytes());
		String result = csvServices.Csvupload(inputStream);
		assertEquals("Csv file upload", result);
		verify(areaRepo,times(1)).save(any(Area.class));
		
		
	}
	
	@Test
	void testCsvMapping()throws IOException
	{
		InputStream inputStream =new ByteArrayInputStream(Csvmapppingcontent.getBytes());
		String result = csvServices.CsvMapping(inputStream);
		assertEquals("Done Csv Mapping", result);
		 verify(areaRepo, times(1)).saveAll(argThat(areas -> {
		        Area a = areas.iterator().next();
		        return a.getTasks().size() == 2;
		    }));
		
	}
	
	
}
