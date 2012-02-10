package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

/**
 * The interface Actor 
 * Provides methods that should be implemented 
 */

public interface Actor {
	
	// The actor is alive by default
	public boolean alive = true;
	// The actor does not have a field by default
	public Field field = null;
	// The actor does not have a location by default
	public Location location = null;
	
	/**
	 * Makes the actor Act
	 * @param the list of Actors
	 */
	public abstract void act(List<Actor> newActors);
	
	/**
	 * To check if the Actor is still alive
	 */
    public abstract boolean isAlive();
    
    /**
     * Sets the Actor as dead, removing it from the field.
     */
    public abstract void setDead();
}