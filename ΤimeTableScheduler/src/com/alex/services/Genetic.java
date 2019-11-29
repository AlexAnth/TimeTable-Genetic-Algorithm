package com.alex.services;


/* AUTHOR: ANTHIS ALEXANDROS 3140012 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.alex.entities.Lesson;
import com.alex.entities.Slots;
import com.alex.entities.Teacher;

public class Genetic {
	
		//ArrayList that contains the current population of chromosomes
		private ArrayList<Chromosome> population;
	    /*
	     * ArrayList that contains indexes of the chromosomes in the population ArrayList
	     * Each chromosome index exists in the ArrayList as many times as its fitness score
	     * By creating this ArrayList so, and choosing a random index from it,
	     * the greater the fitness score of a chromosome the greater chance it will be chosen.
	    */
		private ArrayList<Integer> fitnessBounds;
		
		public Genetic(){
			this.population = null;
			this.fitnessBounds = null;
		}

	    /* 
	     * populationSize: The size of the population in every step
	     * mutationPropability: The propability a mutation might occur in a chromosome
	     * minimumFitness: The minimum fitness value of the solution we wish to find
	     * maximumSteps: The maximum number of steps we will search for a solution
	     */ 
		
		//x o arithmos twn mathimatwn
		public Chromosome geneticAlgorithm(ArrayList<Lesson> lessons,ArrayList<Teacher> teachers,ArrayList<Slots> slots, int populationSize, double mutationProbability, int minimumFitness, int maximumSteps){
	        //We initialize the population
			initializePopulation(lessons,teachers,slots,populationSize);
			Random r = new Random();
			for(int step=0; step < maximumSteps; step++){
				System.out.println("Step is: "+step);
	            //Initialize the new generated population
				ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
				for(int i=0; i < populationSize; i++){
	                //We choose two chromosomes from the population
	                //Due to how fitnessBounds ArrayList is generated, the propability of
	                //selecting a specific chromosome depends on its fitness score
					int xIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
					Chromosome x = this.population.get(xIndex);
					int yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
					while(yIndex == xIndex){
						yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
					}
					Chromosome y = this.population.get(yIndex);
	                //We generate the "child" of the two chromosomes
					Chromosome child = this.reproduce(x,y,9,slots.size());
	                //We might then mutate the child
					if(r.nextDouble() < mutationProbability){
						child.mutate(teachers);
					}
	                //...and finally add it to the new population
					newPopulation.add(child);
				}
				this.population = new ArrayList<Chromosome>(newPopulation);

	            //We sort the population so the one with the greater fitness is first
				Collections.sort(this.population, Collections.reverseOrder());
	            //If the chromosome with the best fitness is acceptable we return it
				if(this.population.get(0).getFitness() >= minimumFitness){
	                System.out.println("Finished after " + step + " steps...");
					System.out.println("Score " +this.population.get(0).getFitness() );
					return this.population.get(0);
				}
	            //We update the fitnessBounds arrayList
				this.updateFitnessBounds();
			}

	        System.out.println("Finished after " + maximumSteps + " steps...");
			System.out.println("Score " +this.population.get(0).getFitness() );
			return this.population.get(0);
		}
		
		public void initializePopulation(ArrayList<Lesson> lessons,ArrayList<Teacher> teachers,ArrayList<Slots> slots,int populationSize){
			this.population = new ArrayList<Chromosome>();
			for(int i=0; i<populationSize; i++){
				this.population.add(new Chromosome(lessons,teachers,slots));
			}
			this.updateFitnessBounds();
		}
		
		//Updates the arraylist that contains indexes of the chromosomes in the population ArrayList
		public void updateFitnessBounds(){
			this.fitnessBounds = new ArrayList<Integer>();
			for (int i=0; i<this.population.size(); i++){
				for(int j=0; j<this.population.get(i).getFitness(); j++){
	                //Each chromosome index exists in the ArrayList as many times as its fitness score
	                //By creating this ArrayList so, and choosing a random index from it,
	                //the greater the fitness score of a chromosome the greater chance it will be chosen.
					fitnessBounds.add(i);
				}
			}
		}
		
		//"Reproduces" two chromosomes and generated their "child"
		public Chromosome reproduce(Chromosome x, Chromosome y, int a,int b){
			Random r = new Random();
	        //Randomly choose the intersection point
			int intersectionPoint = r.nextInt(b-1) ;
			int [] [] childGenes = new int[a] [b];
	        //The child has the left side of the x chromosome up to the intersection point...
			for(int j=0; j<intersectionPoint; j++){
				for (int i=0; i<a; i++){
				childGenes [i] [j] = x.getGenes(i,j);
				}
			}
	        //...and the right side of the y chromosome after the intersection point
			for(int j=intersectionPoint; j<b; j++){
				for (int i=0; i<a; i++){
					childGenes [i] [j] = y.getGenes(i,j);
				}
			}
			return new Chromosome(childGenes);
		}

}
