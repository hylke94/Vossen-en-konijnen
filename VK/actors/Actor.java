package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

/**
 * The interface Actor 
 * Provides methods that should be implemented 
 */

public interface Actor {
	
	public boolean alive = true;
	public Field field = null;
	public Location location = null;
    
	public abstract void act(List<Actor> newActors);
	
    public abstract boolean isAlive();
    
    public abstract void setDead();
}