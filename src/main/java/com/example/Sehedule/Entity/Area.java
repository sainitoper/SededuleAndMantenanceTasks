package com.example.Sehedule.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="area")
public class Area {
	@Id
	private long id;
	private String floor;
	private String wing;
	@OneToMany(mappedBy ="area" , cascade = CascadeType.ALL)
	 @JsonManagedReference
	private List<Task> Tasks;
	
	
	public long getId() {
		return id;
	}
	public String getFloor() {
		return floor;
	}
	public String getWing() {
		return wing;
	}
	public List<Task> getTasks() {
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
	public void setTasks(List<Task> tasks) {
		Tasks = tasks;
	}
	public Area() {
		
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", floor=" + floor + ", wing=" + wing + ", Tasks=" + Tasks + "]";
	}
	
	

}
