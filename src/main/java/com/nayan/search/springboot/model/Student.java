package com.nayan.search.springboot.model;
import java.util.ArrayList;
import java.util.List;

public class Student {
	
	public Student(String id, String name, String description, List<Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.courses = courses;
	}
	private String id;
	private String name;
	private String description;
	private List<Course> courses;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getId() {
		return id;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
}

