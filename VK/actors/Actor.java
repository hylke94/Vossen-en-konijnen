package VK.actors;

import java.util.List;

import VK.view.Field;
import VK.view.Location;

public interface Actor {
	
	public boolean alive = true;
	public Field field = null;
	public Location location = null;
    
	public abstract void act(List<Actor> newActors);
	
    public abstract boolean isAlive();
    
    public abstract void setDead();
}