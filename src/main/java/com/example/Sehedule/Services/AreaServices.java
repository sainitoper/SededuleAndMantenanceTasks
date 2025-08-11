package com.example.Sehedule.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	

	
	public Page<AreaDto> GetAllAreaDetail(String floor,String wing,   Pageable pageable) {
		 Page<Area> areasPage;
		if(floor!=null && wing!=null)
		{
			areasPage=	areaRepo.findByFloorAndWing(floor, wing, pageable);
		}
		else if(floor!=null)
		{
			areasPage=	areaRepo.findByFloor(floor, pageable);
		}
		else if(wing!=null)
		{
			areasPage=areaRepo.findByWing(wing, pageable);
		}
		else {
			areasPage=areaRepo.findAll(pageable);
		}
//	    Page<Area> areasPage = areaRepo.findAll(pageable);
	    return areasPage.map(this::AreaToDto);
}

}
