package com.example.Sehedule.Services;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Repo.AreaRepo;

@Service
public class AreaServices {
	@Autowired
	private AreaRepo areaRepo;
	
	@Autowired
	public ModelMapper mapper;
	
	
	private  AreaDto AreaToDto(Area area)
	{
		return mapper.map(area, AreaDto.class);
	}
	
	private  Area DtoToArea(AreaDto areaDto)
	{
		return mapper.map(areaDto, Area.class);
	}
	public List<AreaDto> GetAllAreaDetails()
	{
		List<Area> areas= areaRepo.findAll();	
		return areas.stream().map(this::AreaToDto).collect(Collectors.toList());
	}
	
	public AreaDto GetAreaDetaisById(long id)
	{
	         
		Area area = areaRepo.findById(id).orElseThrow();
		return AreaToDto(area);
	}

	
	public void SaveDetails(AreaDto areaDto)
	{
		Area area = DtoToArea(areaDto);
		areaRepo.save(area);
	}
	
	public void update(AreaDto areaDto,long id)
	{
		areaDto.setId(id);
		
		Area area = DtoToArea(areaDto);
		
		areaRepo.save(area);
	}
	
	public void delete(long id)
	{
		areaRepo.deleteById(id);
	}
}
