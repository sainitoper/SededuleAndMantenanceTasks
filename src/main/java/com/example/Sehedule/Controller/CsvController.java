package com.example.Sehedule.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Sehedule.Services.CsvServices;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/csvcontro")
public class CsvController {
	
	@Autowired
	private CsvServices csvServices;
	
	@PostMapping(value="/csv-file", consumes = "multipart/form-data")
	@Operation(summary = "Upload CSV file to database",description = "Upload csv Area file")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "File uploaded successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid file", content = @Content(mediaType = "application/json"))

	})
	public String Csvfileuploadtodb(@RequestParam("file") MultipartFile file)
	{
		if(file.isEmpty()|| !file.getOriginalFilename().endsWith(".csv"))
		{
			return "file not found";
		}
		try {
		csvServices.Csvupload(file.getInputStream());
		}catch(Exception e)
		{
			return e.getMessage()
;		}
		return "Csv file upload";
	}
	
	
	@PostMapping(value="/csv-mapping", consumes = "multipart/form-data")
	@Operation(summary = "Upload CSV MAPPING file to database",description = "upload csv mapping file")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "File uploaded successfully of csv"),
			@ApiResponse(responseCode = "400", description = "Invalid file", content = @Content(mediaType = "application/json"))

	})
	public String CsvMapping(@RequestParam("file") MultipartFile file)
	{
		try {
			csvServices.CsvMapping(file.getInputStream());
		}catch (Exception e) {
			return e.getMessage();
		}
		return "Done Csv Mapping";
	}
}
