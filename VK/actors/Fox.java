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
    public static double breedingProbability = 0.30;
    // The maximum number of births.
    public static int maxLitterSize = 3;
	// The food value of a single animal. In effect, this is the
	// number of steps a animal can go before it has to eat again.
 	public static int food_value = 10;

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
        this.foodLevel = food_value;
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
                    setFoodLevel(Fox.food_value);
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
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
    	return maxAge;
    }
}