package com.example.Sehedule.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Entity.Task;
import com.example.Sehedule.Repo.AreaRepo;

@Service
public class CsvServices {
	
	@Autowired
	private AreaRepo areaRepo;
	
	public String Csvupload(InputStream inputStream)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		boolean temp = true;
		while((line=br.readLine())!=null)
		{
			String data[] = line.split(",");
			if(temp)
			{
				temp=false;
				continue;
			}
			Area area = new Area();
			area.setId(Integer.parseInt(data[0]));
			area.setFloor(data[1]);
			area.setWing(data[2]);
			
			areaRepo.save(area);
		}
		return "Csv file upload";
	}
	
	
	public String CsvMapping(InputStream inputStream) throws IOException
	{
		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		boolean temp = true;
		Map<Integer,Area> map = new HashMap<>();
		
		while((line=bReader.readLine())!=null)
		{
			String data[] = line.split(",");
			if(temp)
			{
				temp=false;
				continue;
			}
			Integer areaid = Integer.parseInt(data[0]);
			String floor = data[1];
			String wing = data[2];
			Integer taskid = Integer.parseInt(data[3]);
			String tasktypeString =data[4];
			LocalDate sehduleDate = LocalDate.parse(data[5]);
			String statuString =data[6];
		
		Area area = map.get(areaid);
		if(area==null)
		{
			area = new Area();
			area.setId(areaid);
			area.setFloor(floor);
			area.setWing(wing);
			area.setTasks(new ArrayList<>());
			map.put(areaid, area);
		}
	Task task= new Task();
	task.setId(taskid);
	task.setTasktype(tasktypeString);
	task.setSehduleDate(sehduleDate);
	task.setStatus(statuString);
 area.getTasks().add(task);
			
		}
		areaRepo.saveAll(map.values());
		return "Done Csv Mapping";
	}
	

}
