package VK.animals;
import java.util.List;
import java.util.Random;

import VK.actors.Actor;
import VK.simulator.Field;
import VK.simulator.Location;
import VK.simulator.Randomizer;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Animal implements Actor
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's age
    protected int age;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field1 The field currently occupied.
     * @param location1 The location within the field.
     */
    public Animal(Field fieldInput, Location locationInput)
    {
        this.alive = true;
        this.field = fieldInput;
        setLocation(locationInput);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    @Override
	public boolean isAlive()
    {
        return this.alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    @Override
	public void setDead()
    {
        this.alive = false;
        if(this.location != null) {
            this.field.clear(this.location);
            this.location = null;
            this.field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    @Override
	public Location getLocation()
    {
        return this.location;
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    @Override
	public Field getField()
    {
        return this.field;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    @Override
	public void setLocation(Location newLocation)
    {
        if(this.location != null) {
            this.field.clear(this.location);
        }
        this.location = newLocation;
        this.field.place(this, newLocation);
    }

    /**
     * A animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    public boolean canBreed()
    {
        return this.age >= getBreedingAge();
    }
	abstract protected int getBreedingAge();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
        this.age++;
        if(this.age > getMaxAge()) {
            setDead();
        }
    }
    abstract protected int getMaxAge();
    
    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    protected void incrementHunger()
    {
        int foodlevel = getFoodlevel()-1;
        if(foodlevel <= 0) {
            setDead();
        }
    }
    abstract protected int getFoodlevel();
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
    	int births = 0;
    	if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
    		births = rand.nextInt(getMaxLitterSize()) + 1;
    	}
    	return births;
    }
    abstract protected double getBreedingProbability();
    abstract protected int getMaxLitterSize();
}