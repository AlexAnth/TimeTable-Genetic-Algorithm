package com.alex.entities;

import java.util.ArrayList;

public class Teacher {
	
	private int id;
	private String fullName;
	private Lesson lesson;
	private int maxHoursWeek;
	private int maxHoursDay;

	
	public Teacher(int id, String fullName, Lesson lesson, int maxHoursWeek,int maxHoursDay) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.lesson = lesson;
		this.maxHoursWeek = maxHoursWeek;
		this.maxHoursDay = maxHoursDay;

	}
	
	public Teacher() {}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Lesson getLesson() {
		return lesson;
	}
	
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public int getMaxHoursWeek() {
		return maxHoursWeek;
	}
	
	public void setMaxHoursWeek(int maxHours) {
		this.maxHoursWeek = maxHours;
	}
	public int getMaxHoursDay() {
		return maxHoursDay;
	}
	
	public void setMaxHoursDay(int maxHours) {
		this.maxHoursDay = maxHours;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", fullName=" + fullName + ", lesson=" + lesson + ", maxHoursWeek=" + maxHoursWeek
				+ ", maxHoursDay=" + maxHoursDay + "]";
	}
	
	
	
}
