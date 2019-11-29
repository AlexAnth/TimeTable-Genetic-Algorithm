package com.alex.services;

import java.util.ArrayList;
import java.util.Random;

import com.alex.entities.Lesson;
import com.alex.entities.Slots;
import com.alex.entities.Teacher;
public class Chromosome implements Comparable<Chromosome> {
	
	private int [][] genes;
	private int fitness;
	private  ArrayList<Lesson> lessons;
	private  ArrayList<Teacher> teachers;
	private  ArrayList<Slots> slots;
	
	public Chromosome(ArrayList<Lesson> lessons, ArrayList <Teacher> teachers,ArrayList<Slots> slots){
		this.lessons=lessons;
		this.teachers=teachers;
		this.slots=slots;
		this.genes = new int[9][slots.size()];
		Random r = new Random();
		for(int i=0; i<slots.size(); i++){  // for each slot
			System.out.println("For day "+slots.get(i).getDay()+" at "+slots.get(i).getHour());
			for(int y=0; y<8; y=y+3){       // for each class 
				char asciiClass = (char)(65+ y/3);
				System.out.println("For class "+ asciiClass);
				int randomTeacher=r.nextInt(teachers.size());
				System.out.println(y);
				this.genes[y][i] = teachers.get(randomTeacher).getId(); // teachers id
				System.out.println(y);
				this.genes[y+1][i] = teachers.get(randomTeacher).getLesson().getId(); // teachers lesson id 
				System.out.println(y);
				this.genes[y+2][i] = teachers.get(randomTeacher).getLesson().getClassTeached().getId(); // classes 1-2-3 (ABC)
				System.out.println(y);
			}
		}
		this.calculateFitness();
	}
	
    //Constructs a copy of a chromosome
	public Chromosome(int [] [] genes){
		
		this.genes = new int[genes.length][genes[0].length];
		for(int j=0; j<genes[0].length; j++){
			for (int i=0; i<genes.length; i++)
			this.genes[i][j] = genes[i][j];
		}
		this.calculateFitness();
	}
	
	public ArrayList<Lesson> getLessons(){
		return lessons;
	}
	
	public void setLessons(ArrayList<Lesson> lessons){
		this.lessons=lessons;
	}
	
	public ArrayList<Slots> getSlots() {
		return slots;
	}

	public void setSlots(ArrayList<Slots> slots) {
		this.slots = slots;
	}

	public ArrayList<Teacher> getTeachers(){
		return teachers;
	}
	
	public void setTeachers(ArrayList<Teacher> teachers){
		this.teachers=teachers;
	}
	
	
	public int[] [] getGenes(){
		return this.genes;
		
	}
	public int getGenes(int i,int j){
		return genes[i][j];
		
	}
	
	public int getFitness(){
		return this.fitness;
	}
	
	public void setGenes(int[] [] genes){
		for(int i=0; i<slots.size(); i++){
			for (int j=0; j<9; j++)
			this.genes[i][j] = genes[i][j];
		}
	}
	
	public void setFitness(int fitness){
		this.fitness = fitness;
	}
	
	
	public void calculateFitness(){
		
		int heuristic=0;
		boolean different_teachers=false; 
		boolean different_lessons=false; 
		boolean different_class=false; 	
		boolean three_hours_straight = false;
		
		for(int j=0; j<genes[0].length-1; j++){   //for each slot
			
			if (genes[0][j]!=genes[3][j] && genes[0][j]!=genes[6][j] && genes[3][j]!=genes[6][j] ) different_teachers=true; 
			
			if (genes[1][j]!=genes[4][j] && genes[1][j]!=genes[7][j] && genes[4][j]!=genes[7][j] ) different_lessons=true;

			if (genes[2][j]!=genes[5][j] && genes[2][j]!=genes[8][j] && genes[5][j]!=genes[8][j] ) different_class=true;
			
			if (j+3<genes[0].length) {
				if (genes[0][j]==genes[0][j+1] && genes[0][j+1]==genes[0][j+2] && genes[0][j+2]==genes[0][j+3] ) three_hours_straight=true; 
			}
							
			if(different_lessons) heuristic+=25;
			if(different_class) heuristic+=22;
			if(different_teachers) heuristic+=18;
			if(!three_hours_straight) heuristic+=15;
			
			different_teachers=false; 
			different_lessons=false; 
			different_class=false; 	
			three_hours_straight = false;

		}

		this.fitness = heuristic;
		System.out.println("Fitness: "+ heuristic);
		
	}
	
	
	public void mutate(ArrayList<Teacher> teachers){
		
		Random r = new Random();
		int i = r.nextInt(genes[0].length);
		for(int y=0; y<8; y=y+3){       // for each class 
			int randomTeacher=r.nextInt(teachers.size());
			this.genes[y][i] = teachers.get(randomTeacher).getId(); // teachers id
			this.genes[y+1][i] = teachers.get(randomTeacher).getLesson().getId(); // teachers lesson id 
			this.genes[y+2][i] = teachers.get(randomTeacher).getLesson().getClassTeached().getId(); // classes 1-2-3 (ABC)
		}		
		this.calculateFitness( );
	}
	
	@Override
	public int compareTo(Chromosome x){
		return this.fitness - x.fitness;
	}
}
