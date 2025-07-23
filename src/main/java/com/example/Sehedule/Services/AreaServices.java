package com.example.Sehedule.Services;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Repo.AreaRepo;

@Service
public class AreaServices {
	@Autowired
	private AreaRepo areaRepo;
	
	public List<Area> GetAllAreaDetails()
	{
		return areaRepo.findAll();	
	}
	
	public Area GetAreaDetaisById(long id)
	{
		return areaRepo.findById(id).orElseThrow(null);
	}

	
	public void SaveDetails(Area area)
	{
		areaRepo.save(area);
	}
	
	public void update(Area area,long id)
	{
		area.setId(id);
		areaRepo.save(area);
	}
	
	public void delete(long id)
	{
		areaRepo.deleteById(id);
	}
}
