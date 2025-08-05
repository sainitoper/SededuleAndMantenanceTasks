package com.example.Sehedule.ServicesTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Repo.AreaRepo;
import com.example.Sehedule.Services.AreaServices;

@ExtendWith(MockitoExtension.class)
public class AreaServiceTest {
	
	
	@Mock
	private AreaRepo areaRepo;
	
	@Mock 
	private ModelMapper mapper;
	
	@InjectMocks
	private AreaServices areaServices;
	
	private Area area;
	private AreaDto areaDto;
	@BeforeEach
	void setup()
	{	
		area = new Area();
		area.setId(1L);
		area.setFloor("13th");
		area.setWing("Y");
		
		areaDto = new AreaDto();
		areaDto.setId(1L);
		areaDto.setFloor("13th");
		areaDto.setWing("Y");
	}
	
	@Test
	void testGetAllAreaDetails()
	{
		
		List<Area> areas = List.of(area);
		when(areaRepo.findAll()).thenReturn(areas);
		when(mapper.map(area, AreaDto.class)).thenReturn(areaDto);
		
		List<AreaDto> list = areaServices.GetAllAreaDetails();
		
		assertEquals(1, list.size());
		assertEquals("13th", list.get(0).getFloor());
		verify(areaRepo).findAll();
		
	}
	
	@Test
	void testGetAreaDetaisById()
	{
		when(areaRepo.findById(1L)).thenReturn(Optional.of(area));
		when(mapper.map(area, AreaDto.class)).thenReturn(areaDto);
		
		AreaDto areaDto = areaServices.GetAreaDetaisById(1L);
		
		assertEquals("13th", areaDto.getFloor());
		assertEquals("Y", areaDto.getWing());
		
		verify(areaRepo).findById(1L);
	}
	
	@Test
	void testSaveDetails()
	{
		when(mapper.map(areaDto, Area.class)).thenReturn(area);
		
		areaServices.SaveDetails(areaDto);
		
		verify(areaRepo).save(area);
	}
	
	@Test 
	void testupdate()
	{
		when(mapper.map(areaDto, Area.class)).thenReturn(area);
		areaServices.update(areaDto, 1L);
		
		assertEquals(1L, areaDto.getId());
		
		verify(areaRepo).save(area);
	}
	
	@Test
	void testdelete()
	{
		areaServices.delete(1L);
		
		verify(areaRepo).deleteById(1L);
	}
	

}
