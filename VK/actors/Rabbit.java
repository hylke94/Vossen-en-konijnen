package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Rabbit extends Animal
{
	// Characteristics shared by all rabbits (static fields).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.50;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
	// The food value of a single grass. In effect, this is the
	// number of steps a rabbit can go before it has to eat again.
	private static final int GRASS_FOOD_VALUE = 5;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        this.age = 0;
        if(randomAge) {
            this.age = rand.nextInt(MAX_AGE);
            setFoodLevel(rand.nextInt(MAX_AGE));
        }
        else {
        	this.age = MAX_AGE;
        	setFoodLevel(GRASS_FOOD_VALUE);
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to add newly born rabbits to.
     */
    @Override
    public void act(List<Actor> newRabbits)
    {
        super.incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newRabbits);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
        else {
        	setDead();
        }
    }

	private void incrementHunger() {
		setFoodLevel(getFoodLevel()-1);
		if (getFoodLevel() <= 0) setDead();
	}
    
    @Override
	protected double getBreedingProbability(){
    	return BREEDING_PROBABILITY;
    }
    
    @Override
	protected int getMaxLitterSize(){
    	return MAX_LITTER_SIZE;
    }

	@Override
	protected int getBreedingAge() {
		return BREEDING_AGE;
	}
	
    @Override
	protected int getMaxAge(){
    	return MAX_AGE;
    }
}