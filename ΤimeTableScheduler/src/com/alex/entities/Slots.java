package com.alex.entities;

public class Slots {
	
	private int id;
	private String day;
	private String hour;
	private boolean available; // available refers to day (if saturday not available)
	
	Slots(String day, String hour,int id,boolean available){
		this.day=day;
		this.hour=hour;
		this.id=id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId (){
		return id;
	}
	
	public void setDay(String day){
		this.day=day;
	}
	
	public void setHour(String hour){
		this.hour=hour;
	}
	
	public String getDay(){
		return day;
	}
	
	public String getHour(){
		return hour;
	}
	
	public void setAvailable(boolean available){
		this.available=available;
	}
	
	public boolean getAvailable(){
		return available;
	}

	@Override
	public String toString() {
		return "Slots [id=" + id + ", day=" + day + ", hour=" + hour + ", available=" + available + "]";
	}

	
}
