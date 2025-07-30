package com.example.Sehedule.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.Sehedule.validations.anotations.ValidFloor;
import com.example.Sehedule.validations.anotations.ValidWing;

import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.NotEmpty;

public class AreaDto {
	
	@NotNull(message = "id is not null")
	@Min(value = 0 , message = "id must be greater then 0")
private long id;
public long getId() {
	return id;
}

public String getFloor() {
	return floor;
}

public String getWing() {
	return wing;
}

public List<TaskDto> getTasks() {
	return Tasks;
}

public void setId(long id) {
	this.id = id;
}

public void setFloor(String floor) {
	this.floor = floor;
}

public void setWing(String wing) {
	this.wing = wing;
}

public void setTasks(List<TaskDto> tasks) {
	Tasks = tasks;
}

//@NotBlank(message = "floor is not whiltesapace or null")
@ValidFloor
private String floor;
//@NotNull(message = "wing is not null")
@ValidWing
private String wing;


//@NotEmpty(message = "not null and empty")
List<TaskDto> Tasks= new ArrayList<>();
public AreaDto() {
	
}

@Override
public String toString() {
	return "AreaDto [id=" + id + ", floor=" + floor + ", wing=" + wing + "]";
}

}
