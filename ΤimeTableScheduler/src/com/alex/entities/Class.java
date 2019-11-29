package com.alex.entities;

public class Class {

	private int id;
	private String title;

	public Class(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Class() {}
	
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
	
	@Override
	public String toString() {
		return "Class [id=" + id + ", title=" + title + "]";
	}
	
}



