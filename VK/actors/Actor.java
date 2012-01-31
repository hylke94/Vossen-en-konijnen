package VK.actors;

import VK.simulator.Field;
import VK.simulator.Location;

public interface Actor {
	
	// Whether the animal is alive or not.
    public boolean alive = true;
    // The animal's field.
    public Field field = null;
    // The animal's position in the field.
    public Location location = null;
    
    /**
     * Check whether the actor is alive or not.
     * @return true if the actor is still alive.
     */
    public boolean isAlive();
    
    /**
     * Indicate that the actor is no longer alive.
     * It is removed from the field.
     */
    public void setDead();
    
    /**
     * Return the actors location.
     * @return The actors location.
     */
    public Location getLocation();
    
    /**
     * Return the actors field.
     * @return The actors field.
     */
    public Field getField();
    
    /**
     * Place the actor at the new location in the given field.
     * @param newLocation The actors new location.
     */
    public void setLocation(Location newLocation);
}
