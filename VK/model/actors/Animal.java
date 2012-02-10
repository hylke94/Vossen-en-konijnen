package VK.model.actors;

import java.util.List;
import java.util.Random;

import VK.utils.Randomizer;
import VK.view.Field;
import VK.view.Location;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

public abstract class Animal implements Actor
{
	// Whether the animal is alive or not.
	private boolean alive = true;
	// The animal's field.
	private Field field = null;
	// The animal's position in the field.
	private Location location = null;
	// The animal's age
	protected int age = 0;
	// A shared random number generator to control breeding.
	protected static final Random rand = Randomizer.getRandom();
	// The animal's food level, which is increased by eating rabbits.
	protected int foodLevel = 0;

	/**
	 * Create a new animal at location in field.
	 * 
	 * @param fieldInput The field currently occupied.
	 * @param locationInput The location within the field.
	 */
	public Animal(Field fieldInput, Location locationInput)
	{
		this.alive = true;
		this.field = fieldInput;
		setLocation(locationInput);
	}
	/**
	 * Set the foodlevel for an Animal;
	 * @param int newFoodlevel the new foodlevel
	 */
	
	public void setFoodLevel(int newFoodLevel){
		this.foodLevel = newFoodLevel;
	}

	/**
	 * Returns the foodlevel
	 * @return the foodlevel
	 */
	
	public int getFoodLevel(){
		return this.foodLevel;
	}

	/**
	 * Make this animal act - that is: make it do
	 * whatever it wants/needs to do.
	 * @param newAnimals A list to add newly born animals to.
	 */
	@Override
	public abstract void act(List<Actor> newActors);

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
	public Location getLocation()
	{
		return this.location;
	}

	/**
	 * Return the animal's field.
	 * @return The animal's field.
	 */
	public Field getField()
	{
		return this.field;
	}

	/**
	 * Place the animal at the new location in the given field.
	 * @param newLocation The animal's new location
	 */
	public void setLocation(Location newLocation){
		if (this.location != null) this.field.clear(this.location);
		this.location = newLocation;
		this.field.place(this, newLocation);
	}

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

	/**
	 * Returns the breeding probability of an animal.
	 */
	abstract protected double getBreedingProbability();

	/**
	 * Returns the maximum amount of offspring an animal can have.
	 */
	abstract protected int getMaxLitterSize();

	/**
	 * Return the animal's age.
	 * @return The animal's age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Set's the animal's age.
	 * @param The animal's age.
	 */
	public void setAge(int ageInput) {
		this.age=ageInput;
	}

	/**
	 * Increase the age. This could result in the fox's death.
	 */
	protected void incrementAge() {
		this.age++;
		if(this.age > getMaxAge()) setDead();
	}

	/**
	 * Increase the age. This could result in the fox's death.
	 */
	protected void incrementHunger() {
		this.foodLevel--;
		if (this.foodLevel <= 0) setDead();
	}

	/**
	 * Returns the maximum age of an animal
	 */
	abstract protected int getMaxAge();

	/**
	 * A fox can breed if it has reached the breeding age.
	 */
	public boolean canBreed() {
		return (this.age >= getBreedingAge());
	}

	abstract protected int getBreedingAge();

	/**
	 * Check whether or not this fox is to give birth at this step.
	 * New births will be made into free adjacent locations.
	 * @param newAnimals A list to add newly born animals to.
	 */
	protected void giveBirth(List<Actor> newAnimals) {

		// New actors are born into adjacent locations.
		// Get a list of adjacent free locations.

		Animal animal = this;
		Animal young = null;
		Field currentField = getField();
		List<Location> free = currentField.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			if (animal instanceof Fox){
				young = new Fox(false, currentField, loc);
			}
			else if(animal instanceof Rabbit){
				young = new Rabbit(false, currentField, loc);
			}
			else if(animal instanceof Bear){
				young = new Bear(false, currentField, loc);
			}
			else if(animal instanceof Grass){
				young = new Grass(currentField, loc);
			}
			newAnimals.add(young);
		}
	}
}