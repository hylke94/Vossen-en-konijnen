package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

/**
 * The class Grass
 * Creates and has all the settings for Grass
 * @author Pim
 *
 */
public class Grass extends Animal{
	
	// Characteristics shared by all foxes (static fields).
    
    // The age at which a fox can start to breed.
    public static int breedingAge = 1;
    // The likelihood of a fox breeding.
    public static double breedingProbability = 0.45;
    // The maximum number of births.
    public static int maxLitterSize = 1;
    
    /**
     * Constructs a new piece of Grass
     * 
     * @param Field fieldInput
     * @param LocationlocationInput
     */
	public Grass(Field fieldInput, Location locationInput) {
		super(fieldInput, locationInput);
		this.age = 1;
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
	protected double getBreedingProbability() {
		return Fox.breedingProbability;
	}

	public static int getBreedingProbabilityInt() {
		int i = ((int) (breedingProbability*100));
		return i;
	}

	public static void setBreedingProbabilityInt(int j) {
		breedingProbability = ((double) j/100);
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