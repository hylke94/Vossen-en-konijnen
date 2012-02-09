package VK.actors;

import java.util.List;
import java.util.Random;

import VK.view.Field;
import VK.view.Location;

public class Hunter extends Human {
	
	private int kills = 0;
	public static int maxKills = 20;
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new hunter at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if (randomAge) this.age = rand.nextInt();
        else this.age=0;
        this.kills = rand.nextInt(Hunter.maxKills);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    @Override
	public void act(List<Actor> newHunters)
    {
        if(isAlive()) {
        	// Move towards a source of food if found
        	Location location = getLocation();
            Location newLocation = findTarget();
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
        	if (this.kills >= Hunter.maxKills) setDead();
        }
        else setDead();
    }
    
    /**
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findTarget()
    {
        Field currentField = getField();
        List<Location> adjacent = currentField.adjacentLocations(getLocation());
        
        for (int i=0; i<Hunter.maxKills; i++) {
        	Location targetLocation = getRandomLocation(adjacent);
        	
            Object object = currentField.getObjectAt(targetLocation);
            
            if (object instanceof Grass) {
            	return null;
            }
            
            if(object instanceof Animal) {
                Animal prey = (Animal) object;
                if(prey.isAlive()) { 
                    prey.setDead();
                    this.kills++;
                    return targetLocation;
                }
            }
        }
        return null;
    }
    
    private static Location getRandomLocation(List<Location> location){
    	int random = (int) (Math.random()*(location.size() -0));
    	return location.get(random);
    }
}
