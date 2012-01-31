package VK.actors;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import VK.animals.Animal;
import VK.simulator.Field;
import VK.simulator.Location;
import VK.simulator.Randomizer;

public class Hunter implements Actor {
	// Whether the hunter is alive or not.
    private boolean alive;
    // The hunters field.
    private Field field;
    // The hunters position in the field.
    private Location location;
    // The hunters age.
    private int age;
    // The hunters max-age.
    private static final int MAX_AGE = 100;
    
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field1 The field currently occupied.
     * @param location1 The location within the field.
     */
    public Hunter(boolean randomAge, Field field1, Location location1)
    {
        this.alive = true;
        this.field = field1;
        setLocation(location1);
        this.age = (randomAge) ? rand.nextInt(MAX_AGE) : 0;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    public void act()
    {
    	incrementAge();
        if(isAlive()) {            
            Location newLocation = findPrey();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(this.location);
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
    
    public void incrementAge()
    {
    	this.age++;
    	if(this.age>MAX_AGE) this.setDead();
    }
    
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
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findPrey()
    {
        Field currentField = getField();
        List<Location> adjacent = currentField.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = currentField.getObjectAt(where);
            if(animal instanceof Animal) {
                Animal prey = (Animal) animal;
                if(prey.isAlive()) { 
                    prey.setDead();
                    return where;
                }
            }
        }
        return null;
    }
    
}
