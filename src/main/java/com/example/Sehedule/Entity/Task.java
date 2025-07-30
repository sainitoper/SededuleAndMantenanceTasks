package com.example.Sehedule.Entity;

import java.time.LocalDate;

import com.example.Sehedule.CustomException.StatusTpyeNotNullException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	@Id
	private long id;
	private String tasktype;
	private LocalDate sehduleDate;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="area_id")
    @JsonBackReference
	private Area area;
	
	public long getId() {
		return id;
	}

	public String getTasktype() {
		
		return tasktype;
	}

	public LocalDate getSehduleDate() {
		return sehduleDate;
	}

	public Area getArea() {
		return area;
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

	public void setArea(Area area) {
		this.area = area;
	}

	public Task() {
		
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", tasktype=" + tasktype + ", sehduleDate=" + sehduleDate + ", status=" + status
				+ ", area=" + area + "]";
	}
	

	

}
