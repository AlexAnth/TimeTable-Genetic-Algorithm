package com.alex.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.alex.entities.Lesson;
import com.alex.entities.ScheduleSlotPOJO;
import com.alex.entities.Slots;
import com.alex.entities.Teacher;
import com.alex.services.Chromosome;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonHandler {

	private Gson g;
	
	public JsonHandler() {
		g = new Gson();
	}
	
	public Lesson[] parseLessons() {
		Lesson[] lessons;
		try {
			lessons = g.fromJson(new FileReader("./src/META/lessons.json"), Lesson[].class);
			return lessons;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public Teacher[] parseTeachers() {
		Teacher[] lessons;
		try {
			lessons = g.fromJson(new FileReader("./src/META/teachers.json"), Teacher[].class);
			return lessons;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public Slots[] parseSlots() {
		Slots[] slots;
		try {
			slots = g.fromJson(new FileReader("./src/META/slots.json"), Slots[].class);
			return slots;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public void writeToTXTFile(String path,Chromosome x,ArrayList<Slots> slots, ArrayList<Lesson> lessons,ArrayList<Teacher> teachers){
		File file = new File(path);
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write("FINAL PROGRAM" + "\n" + "\n");
			for (int i=0; i<slots.size(); i++){
 
				writer.write("Day: " +slots.get(i).getDay() +"\n" );
				writer.write("Hour: "+slots.get(i).getHour() + "\n\n");
				
				for(int y=0; y<8; y=y+3){       // for each class 
					char asciiClass = (char)(65+ y/3);
					writer.write("Class: "+ asciiClass+"\n");
					
				
					for(Teacher t : teachers) {
						if(t.getId()==x.getGenes(y,i)) {   // if teacher id is the same as in the gene
							writer.write("Teacher: "+t.getFullName()+ "\n");
						}	
					}
					
					for(Lesson l : lessons) {
						if(l.getId()==x.getGenes(y+1,i)) {   // if lesson id is the same as in the gene
							writer.write("Lesson: "+l.getTitle()+ "\n\n");
						}	
					}
				
				}
			}
			
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToJSONFile(String path, Chromosome x, ArrayList<Slots> slots, ArrayList<Lesson> lessons,ArrayList<Teacher> teachers) {
		ScheduleSlotPOJO [] final_schedule = new ScheduleSlotPOJO[slots.size()];
		for (int i=0; i<slots.size(); i++){
			 ScheduleSlotPOJO s = new ScheduleSlotPOJO();
			 s.setDay(slots.get(i).getDay());
			 s.setHour(slots.get(i).getHour());
			 for(int y=0; y<8; y=y+3){       // for each class 
					char asciiClass = (char)(65+ y/3);
					
					if (asciiClass=='A') s.setClass_teached_1("A");
					else if (asciiClass=='B') s.setClass_teached_2("B");
					else if (asciiClass=='C') s.setClass_teached_3("C");
				
					for(Teacher t : teachers) {
						if(t.getId()==x.getGenes(y,i)) {   // if teacher id is the same as in the gene
							if (asciiClass=='A') s.setTeacher_1(t.getFullName());
							else if (asciiClass=='B') s.setTeacher_2(t.getFullName());
							else if (asciiClass=='C') s.setTeacher_3(t.getFullName());
						}
					}	
					
					for(Lesson l : lessons) {
						if(l.getId()==x.getGenes(y+1,i)) {   // if lesson id is the same as in the gene
							if (asciiClass=='A') s.setLesson_1(l.getTitle());
							else if (asciiClass=='B') s.setLesson_2(l.getTitle());
							else if (asciiClass=='C') s.setLesson_3(l.getTitle());
						}
					}					
			}
			final_schedule[i]=s;
			 
		}
		try (Writer writer = new FileWriter(path)) {
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    gson.toJson(final_schedule, writer);
		}catch (JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
