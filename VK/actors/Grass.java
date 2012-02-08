package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

public class Grass extends Animal{
	
	// Characteristics shared by all foxes (static fields).
    
    // The age at which a fox can start to breed.
    private static int breedingAge = 0;
    // The likelihood of a fox breeding.
    private static double breedingProbability = 0.10;
    // The maximum number of births.
    private static int maxLitterSize = 1;
    
    /**
     * Constructor
     * 
     * @param fieldInput
     * @param locationInput
     */
	public Grass(Field fieldInput, Location locationInput) {
		super(fieldInput, locationInput);
	}
    
    /**
     * This is what the grass does most of the time: "breeding".
     * In the process, it might breed 
     * @param field The field currently occupied.
     * @param newGrass A list to add newly born grass to.
     */
    @Override
    public void act(List<Actor> newGrass)
    {
        if(isAlive()) {
            giveBirth(newGrass);
        }
    }
	
	@Override
	protected double getBreedingProbability(){
    	return breedingProbability;
    }    
    
    @Override
	protected int getMaxLitterSize(){
    	return maxLitterSize;
    }
    
	@Override
	protected int getBreedingAge() {
		return breedingAge;
	}
	
    @Override
	protected int getMaxAge(){
    	return 0;
    }
}