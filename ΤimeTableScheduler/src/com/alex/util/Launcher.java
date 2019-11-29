package com.alex.util;

import java.util.ArrayList;
import java.util.Arrays;

import com.alex.entities.Lesson;
import com.alex.entities.Slots;
import com.alex.entities.Teacher;
import com.alex.services.Chromosome;
import com.alex.services.Genetic;
import com.google.gson.JsonObject;

public class Launcher {
	
	private static ArrayList <Lesson> lessons = new ArrayList<Lesson>();
	private static ArrayList <Teacher> teachers = new ArrayList<Teacher>();
	private static ArrayList <Slots> slots = new ArrayList<Slots>();
	private static boolean jsonOutput=true;

	public static void main(String[] args) {

		JsonHandler j = new JsonHandler();
		
		Lesson[] l = j.parseLessons();
		Arrays.stream(l).forEach(System.out::println);
		lessons = new ArrayList<Lesson>(Arrays.asList(l));
		System.out.println();

		Teacher[] t = j.parseTeachers();
		Arrays.stream(t).forEach(System.out::println);
		teachers = new ArrayList<Teacher>(Arrays.asList(t));
		System.out.println();

		Slots[] s = j.parseSlots();
		Arrays.stream(s).forEach(System.out::println);
		slots = new ArrayList<Slots>(Arrays.asList(s));
		System.out.println();

		Genetic g = new Genetic();
		Chromosome x = g.geneticAlgorithm(lessons,teachers,slots,30, 0.09,2500,1000);
		
		if(jsonOutput) {
			j.writeToJSONFile("schedule.json",x, slots, lessons, teachers);
		}else {
			j.writeToTXTFile("schedule.txt",x, slots, lessons, teachers);
		}
		
	}
	
}
