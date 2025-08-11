package com.example.Sehedule.Controller;

//import org.springframework.data.domain.Pageable;
import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Services.AreaServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/area")
public class AreaControler {

    
	@Autowired
	private AreaServices areaServices;

   
	
	@GetMapping("/example")
	@Operation(summary = "Get example data", description = "Fetch area data by Getall")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),

	    @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
	})
	public List<AreaDto> getAll()
	{
		return areaServices.GetAllAreaDetails();
	}
	
	
	@PostMapping
	@Operation(summary = "Post data area", description = "Insert Area")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Area successfully created"),
	    @ApiResponse(responseCode = "400", description = "Invalid Area data provided"),
	    @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public String Insert( @Valid @RequestBody AreaDto areaDto)
	{
		areaServices.SaveDetails(areaDto);
		return "Done";
	}
	@GetMapping("/example/page")
	@Operation(summary = "Get example pagedata", description = "Fetch area data by paging Getall")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Successfully retrieved pagealbe list of areas"),
	   
	    @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
	})
	public Page<AreaDto> getUsersPaginatedAndSorted(
			@RequestParam(required = false) String floor,
            @RequestParam(required = false) String wing,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "15") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortDir
	) {
	    Sort sort = sortDir.equalsIgnoreCase("desc") ?
	            Sort.by(sortBy).descending() :
	            Sort.by(sortBy).ascending();

	    Pageable pageable = PageRequest.of(page, size, sort);
	    return areaServices.GetAllAreaDetail(floor,wing , pageable);
	}


	

}
