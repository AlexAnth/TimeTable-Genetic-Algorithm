package com.alex.entities;

public class Lesson {

	private int id;
	private String title;
	private Class classTeached;
	private int hours;
	
	public Lesson(int id, String title, Class classTeached, int hours) {
		this.id = id;
		this.title = title;
		this.classTeached = classTeached;
		this.hours = hours;
	}
	
	public Lesson() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Class getClassTeached() {
		return classTeached;
	}
	
	public void setClassTeached(Class classTeached) {
		this.classTeached = classTeached;
	}
	
	public int getHours() {
		return hours;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", title=" + title + ", classTeached=" + classTeached + ", hours=" + hours + "]";
	}
	
}
