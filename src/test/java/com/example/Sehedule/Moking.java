package com.example.Sehedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;
import com.example.Sehedule.Entity.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Repo.AreaRepo;
import com.example.Sehedule.Services.AreaServices;




@ExtendWith(MockitoExtension.class)
public class Moking {
	
	@Mock
	private AreaRepo repo;

	@InjectMocks
	private AreaServices areaServices;
	
	
	@Autowired
	public ModelMapper mapper;
	
	
	@BeforeEach
	void setup() {
	    mapper = new ModelMapper();  
	}
	
	
	public AreaDto getAreaById(Long id) {
	    Optional<Area> area = repo.findById(id);
	    return area.map(a -> mapper.map(a, AreaDto.class)).orElse(null);
	}

	@Test
	void checking()
	{
		Area area =new Area();
		area.setId(2L);
		area.setFloor("2nd");
		area.setWing("W");
		when(repo.findById(2L)).thenReturn(Optional.of(area));
		
		
		  AreaDto areaDto = getAreaById(2L);
		assertEquals(2L, areaDto.getId());
        assertEquals("2nd", areaDto.getFloor());
        assertEquals("W", areaDto.getWing());
        
        verify(repo).findById(2L);
        
        
	}

}
