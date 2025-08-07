package com.example.Sehedule.Dtos;

import java.time.LocalDate;

import com.example.Sehedule.CustomException.StatusTpyeNotNullException;
import com.example.Sehedule.Entity.Area;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskDto {
//	@NotNull(message = "id is not null")
	@Min(value = 0 , message = "id must be greater then 0")
	private long id;
	
	@NotBlank(message = "tasktype null and whitespace only")
	private String tasktype;
	public long getId() {
		return id;
	}
	public String getTasktype() {
		return tasktype;
	}
	public LocalDate getSehduleDate() {
		return sehduleDate;
	}
	public String getStatus() {
		return status;
	}
	public AreaDto getAreaDto() {
		return areaDto;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTasktype(String tasktype) {
		
		this.tasktype = tasktype;
	}
	public void setSehduleDate(LocalDate sehduleDate) {
		this.sehduleDate = sehduleDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}
	private LocalDate sehduleDate;
	@NotNull(message = "status is not null")
	private String status;
	
	@NotNull(message = "not null and empty")
	private AreaDto areaDto;
	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", tasktype=" + tasktype + ", sehduleDate=" + sehduleDate + ", status=" + status
				+ ", areaDto=" + areaDto + "]";
	}
	public TaskDto() {
		
	}
	
}
