package com.example.Sehedule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Services.AreaServices;
import com.example.Sehedule.Services.CsvServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/area")
public class AreaControler {
	@Autowired
	private AreaServices areaServices;
	@Autowired
	private CsvServices csvServices;
	
	@GetMapping("/example")
	@Operation(summary = "Get example data")
	@ApiResponses(value = {
	    //@ApiResponse(responseCode = "200", description = "Success"),
	    //@ApiResponse(responseCode = "404", description = "Not Found")
	})
	public List<AreaDto> getAll()
	{
		return areaServices.GetAllAreaDetails();
	}
	
	
	@PostMapping
	public String Insert( @Valid @RequestBody AreaDto areaDto)
	{
		areaServices.SaveDetails(areaDto);
		return "Done";
	}
	
	

}
