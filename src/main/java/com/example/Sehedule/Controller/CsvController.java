package com.example.Sehedule.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Sehedule.Services.CsvServices;
@RestController
@RequestMapping("/csvcontro")
public class CsvController {
	
	@Autowired
	private CsvServices csvServices;
	
	@PostMapping("/csv-file")
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
	
	
	@PostMapping("/csv-mapping")
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
