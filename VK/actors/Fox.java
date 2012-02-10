package VK.actors;

import java.util.List;
import java.util.Iterator;

import VK.view.Field;
import VK.view.Location;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Fox extends Animal
{
	// Characteristics shared by all foxes (static fields).
    
    // The age at which a fox can start to breed.
	public static int breedingAge = 10;
    // The age to which a fox can live.
    public static int maxAge = 50;
    // The likelihood of a fox breeding.
    public static double breedingProbability = 0.40;
    // The maximum number of births.
    public static int maxLitterSize = 5;
	// The food value of a single animal. In effect, this is the
	// number of steps a animal can go before it has to eat again.
 	public static int foodValue = 10;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            this.age = rand.nextInt(maxAge);
        }
        else {
            this.age = 0;
        }
        this.foodLevel = foodValue;
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newFoxes A list to add newly born foxes to.
     */
    @Override
    public void act(List<Actor> newFoxes)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);            
            // Move towards a source of food if found.
            Location location = getLocation();
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
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    setFoodLevel(Fox.foodValue);
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
	
	/**
	 * Returns the breeding probability of a fox
	 * @return the breedingprobability
	 */
	@Override
	protected double getBreedingProbability() {
		return Fox.breedingProbability;
	}
	
	/**
	 * Returns the breeding probability of a fox as Integer
	 * @return the breedingprobability
	 */
	public static int getBreedingProbabilityInt() {
		int i = ((int) (breedingProbability*100));
		return i;
	}
	
	/**
	 * Sets the breeding probability
	 * @param int j the new breeding probability
	 */
	public static void setBreedingProbabilityInt(int j) {
		breedingProbability = ((double) j/100);
	}  
	
	/**
	 * Gets the maximum litter size of a Fox
	 * @return int the maximum litter size
	 */
    @Override
	protected int getMaxLitterSize(){
    	return maxLitterSize;
    }
    
	/**
	 * Gets the minimum breeding age of a Fox
	 * @return int the breeding age
	 */
	@Override
	protected int getBreedingAge() {
		return breedingAge;
	}
	
	/**
	 * Returns the maximum age a Fox can get
	 * @return the maximum age
	 */
    @Override
	protected int getMaxAge(){
    	return maxAge;
    }
}