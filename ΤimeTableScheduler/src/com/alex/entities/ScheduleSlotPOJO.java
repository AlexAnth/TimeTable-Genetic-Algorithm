package com.alex.entities;

public class ScheduleSlotPOJO {
	String day;
	String hour;
	String class_teached_1;
	String teacher_1;
	String lesson_1;
	String class_teached_2;
	String teacher_2;
	String lesson_2;
	String class_teached_3;
	String teacher_3;
	String lesson_3;
	
	public ScheduleSlotPOJO() {	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getClass_teached_1() {
		return class_teached_1;
	}

	public void setClass_teached_1(String class_teached_1) {
		this.class_teached_1 = class_teached_1;
	}

	public String getTeacher_1() {
		return teacher_1;
	}

	public void setTeacher_1(String teacher_1) {
		this.teacher_1 = teacher_1;
	}

	public String getLesson_1() {
		return lesson_1;
	}

	public void setLesson_1(String lesson_1) {
		this.lesson_1 = lesson_1;
	}

	public String getClass_teached_2() {
		return class_teached_2;
	}

	public void setClass_teached_2(String class_teached_2) {
		this.class_teached_2 = class_teached_2;
	}

	public String getTeacher_2() {
		return teacher_2;
	}

	public void setTeacher_2(String teacher_2) {
		this.teacher_2 = teacher_2;
	}

	public String getLesson_2() {
		return lesson_2;
	}

	public void setLesson_2(String lesson_2) {
		this.lesson_2 = lesson_2;
	}

	public String getClass_teached_3() {
		return class_teached_3;
	}

	public void setClass_teached_3(String class_teached_3) {
		this.class_teached_3 = class_teached_3;
	}

	public String getTeacher_3() {
		return teacher_3;
	}

	public void setTeacher_3(String teacher_3) {
		this.teacher_3 = teacher_3;
	}

	public String getLesson_3() {
		return lesson_3;
	}

	public void setLesson_3(String lesson_3) {
		this.lesson_3 = lesson_3;
	}

	@Override
	public String toString() {
		return "ScheduleSlotPOJO [day=" + day + ", hour=" + hour + ", class_teached_1=" + class_teached_1
				+ ", teacher_1=" + teacher_1 + ", lesson_1=" + lesson_1 + ", class_teached_2=" + class_teached_2
				+ ", teacher_2=" + teacher_2 + ", lesson_2=" + lesson_2 + ", class_teached_3=" + class_teached_3
				+ ", teacher_3=" + teacher_3 + ", lesson_3=" + lesson_3 + "]";
	}
	
	
}
