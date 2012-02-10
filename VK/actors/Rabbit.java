package VK.actors;

import java.util.Iterator;
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
    public static int breedingAge = 5;
    // The age to which a rabbit can live.
    public static int maxAge = 40;
    // The likelihood of a rabbit breeding.
    public static double breedingProbability = 0.75;
    // The maximum number of births.
    public static int maxLitterSize = 10;
	// The food value of a single animal. In effect, this is the
	// number of steps a animal can go before it has to eat again.
    public static int foodValue = 15;
    
    public boolean isInfected = false;
    public int stepsTillDead = 20;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param fieldInput The field currently occupied.
     * @param locationInput The location within the field.
     */
    public Rabbit(boolean randomAge, Field fieldInput, Location locationInput)
    {
        super(fieldInput, locationInput);
        if(randomAge) {
            this.age = rand.nextInt(maxAge);
        }
        else {
        	this.age = 0;
        }
        this.foodLevel = foodValue;
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to add newly born rabbits to.
     */
    @Override
    public void act(List<Actor> newRabbits)
    {
        incrementAge();
        if (this.isInfected) {
        	this.stepsTillDead--;
        }
        incrementHunger();
        if(isAlive()) {
        	if (this.stepsTillDead <= 0){
        		setDead();
        	}
        	else {
	            giveBirth(newRabbits);            
	            // Move towards a source of food if found.
	            Location location = getLocation();
	            spreadInfection(location);
	            Location newLocation = findFood(location);
	            if(newLocation == null) { 
	                // No food found - try to move to a free location.
	                newLocation = getField().freeAdjacentLocation(location);
	            }
	            // See if it was possible to move.
	            if(newLocation != null) {
	                setLocation(newLocation);
	            }
	            else {
	                // Overcrowding.
	                setDead();
	            }
        	}
        }
    }

	/**
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
	private Location findFood(@SuppressWarnings("unused") Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Grass) {
                Grass grass = (Grass) animal;
                if(grass.isAlive()) { 
                    grass.setDead();
                    setFoodLevel(Rabbit.foodValue);
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
	
	/**
	 * Gets the max litter size of a Rabbit
	 * @return int the maximum litter size
	 */
    
    @Override
	protected int getMaxLitterSize(){
    	return maxLitterSize;
    }
    
    /**
     * Gets the breeding age of a Rabbit
     * @return int the breeding age
     */

	@Override
	protected int getBreedingAge() {
		return breedingAge;
	}
	
    /**
     * Gets the breeding age of a Rabbit
     * @return int the age
     */
	
    @Override
	protected int getMaxAge(){
    	return maxAge;
    }
    
    /**
     * Gets the breeding probability of a Rabbit
     * @return int the breeding probability
     */
	
	@Override
	protected double getBreedingProbability() {
		return Fox.breedingProbability;
	}
	
	/**
	 * Gets the breeding probability as interger
	 * @return int the breeding probability
	 */

	public static int getBreedingProbabilityInt() {
		int i = ((int) (breedingProbability*100));
		return i;
	}
	
	/**
	 * Set the breeding probability as integer
	 * @param j
	 */

	public static void setBreedingProbabilityInt(int j) {
		breedingProbability = ((double) j/100);
	}
	
	/**
	 * Makes the Rabbit spread the infection
	 * @param Location The location of the rabbit and where the infection should start spreading
	 */
	
	private void spreadInfection(@SuppressWarnings("unused") Location location) {
     Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            
            if(actor instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) actor;
                if(rabbit.isAlive()) {
                    rabbit.makeSick();
                }
            }
            
        }

    }
	
	/**
	 * Makes the Rabbit sick
	 */
    
    public void makeSick() {
    	this.isInfected = true;
    }
    
    /**
     * Returns the status of the Rabbit
     * @return boolean infected or not
     */
    public boolean getSick() {
    	return this.isInfected;
    }
}