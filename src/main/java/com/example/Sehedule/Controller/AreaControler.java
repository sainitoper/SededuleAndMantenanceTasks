package com.example.Sehedule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Services.AreaServices;

@RestController
@RequestMapping("/area")
public class AreaControler {
	@Autowired
	private AreaServices areaServices;
	
	
	@GetMapping
	public List<Area> getAll()
	{
		return areaServices.GetAllAreaDetails();
	}
	
	
	@PostMapping
	public String Insert(@RequestBody Area area)
	{
		areaServices.SaveDetails(area);
		return "Done";
	}
	
	
	

}
