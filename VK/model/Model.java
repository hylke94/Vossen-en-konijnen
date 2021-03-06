package VK.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import VK.main.Main;
import VK.model.actors.Actor;
import VK.model.actors.Bear;
import VK.model.actors.Fox;
import VK.model.actors.Grass;
import VK.model.actors.Hunter;
import VK.model.actors.Rabbit;
import VK.utils.Randomizer;
import VK.utils.sound.SoundPlayer;
import VK.view.Field;
import VK.view.FieldStats;
import VK.view.Location;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

public class Model extends AbstractModel implements Runnable{

	// Constants representing configuration information for the simulation.
	// The default width for the grid.
	private static final int DEFAULT_WIDTH = 100;
	// The default depth of the grid.
	private static final int DEFAULT_DEPTH = 100;
	// The probability that a fox will be created in any given grid position.
	private static final double FOX_CREATION_PROBABILITY = 0.05;
	// The probability that a rabbit will be created in any given grid position.
	private static final double RABBIT_CREATION_PROBABILITY = 0.08;
	// The probability that a bear will be created in any given grid position.
	private static final double BEAR_CREATION_PROBABILITY = 0.05;
	// The probability that a hunter will be created in any given grid position.
	private static final double HUNTER_CREATION_PROBABILITY = 0.008;
	// The probability that a grass will be created in any given grid position.
	private static final double GRASS_CREATION_PROBABILITY = 0.008;

	// Colors used for empty locations.
	private static final Color EMPTY_COLOR = Color.white;
	// Color used for objects that have no defined color.
	private static final Color UNKNOWN_COLOR = Color.gray;

	// List of animals in the field.
	private List<Actor> actors;

	// The current state of the field.
	private Field field;
	// The current step of the simulation.
	public int step;
	// A graphical view of the simulation.

	public boolean run = false;
	public static int waitTime = 100;

	private static LinkedHashMap<Class<?>, Color> colors;

	// A statistics object computing and storing simulation information
	private FieldStats stats;

	private Main simulator;

	/**
	 * Constructor
	 * @param newSimulator
	 */
	public Model(Main newSimulator){
		this(DEFAULT_DEPTH, DEFAULT_WIDTH);

		this.simulator = newSimulator;
	}

	public Model(int depth, int width){
		super();

		if(width <= 0 || depth <= 0) {
			System.out.println("The dimensions must be greater than zero.");
			System.out.println("Using default values.");
			this.field = new Field(DEFAULT_DEPTH, DEFAULT_WIDTH);
		}
		else {
			this.field = new Field(depth, width);
		}

		this.actors = new ArrayList<Actor>();

		this.stats = new FieldStats();

		Model.colors = new LinkedHashMap<Class<?>, Color>();

		// Create a view of the state of each location in the field
		setColor(Rabbit.class, Color.orange);
		setColor(Fox.class, Color.blue);
		setColor(Bear.class, Color.red);
		setColor(Hunter.class, Color.black);
		setColor(Grass.class, Color.green);
	}

	/**
	 * Run the simulation from its current state for a reasonably long period,
	 * e.g. 500 steps.
	 */
	public void runLongSimulation()
	{
		simulate(500);
	}

	/**
	 * Run the simulation from its current state for the given number of steps.
	 * Stop before the given number of steps if it ceases to be viable.
	 * @param numSteps The number of steps to run for.
	 */
	public void simulate(int numSteps)
	{
		for(int steps = 1; steps <= numSteps && isViable(this.field); steps++) {
			simulateOneStep();
		}
	}

	/**
	 * Run the simulation from its current state for a single step.
	 * Iterate over the whole field updating the state of each
	 * fox and rabbit.
	 */
	public void simulateOneStep()
	{
		this.step++;

		// Provide space for newborn animals.
		List<Actor> newActors = new ArrayList<Actor>();

		try {
			// Let all rabbits act.
			for(Iterator<Actor> it = this.actors.iterator(); it.hasNext(); ) {
				Actor actor = it.next();
				actor.act(newActors);
				if(! actor.isAlive()) {
					it.remove();
				}
			}
			this.actors.addAll(newActors);
			showStatus(this.step, this.field);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	/**
	 * Reset the simulation to a starting position.
	 */
	public void reset()
	{
		this.step = 0;
		this.actors.clear();
		populate();

		// Show the starting state in the view.
		showStatus(this.step, this.field);
	}

	/**
	 * Randomly populate the field with foxes and rabbits.
	 */
	private void populate()
	{
		Random rand = Randomizer.getRandom();
		this.field.clear();
		for(int row = 0; row < this.field.getDepth(); row++) {
			for(int col = 0; col < this.field.getWidth(); col++) {
				if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Fox fox = new Fox(true, this.field, location);
					this.actors.add(fox);
				}
				else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Rabbit rabbit = new Rabbit(true, this.field, location);
					this.actors.add(rabbit);
				}
				else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Bear bear = new Bear(false, this.field, location);
					this.actors.add(bear);
				}
				else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Hunter hunter = new Hunter(true, this.field, location);
					this.actors.add(hunter);
				}
				else if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Grass grass = new Grass(this.field, location);
					this.actors.add(grass);
				}
			}
		}
	}

	/**
	 * Paint on grid location on this field in a given color.
	 */
	public void drawMark(int x, int y, Color color)
	{
		this.views.get(0).g.setColor(color);
		this.views.get(0).g.fillRect(x * this.views.get(0).xScale, y * this.views.get(0).yScale, this.views.get(0).xScale-1, this.views.get(0).yScale-1);
	}


	/**
	 * Show the current status of the field.
	 * @param stepInput Which iteration step it is.
	 * @param fieldInput The field whose status is to be displayed.
	 */
	public void showStatus(int stepInput, Field fieldInput)
	{
		if(!this.views.get(0).isVisible()) {
			this.views.get(0).setVisible(true);
		}

		this.simulator.lblSteps.setText(this.simulator.STEP_PREFIX + stepInput);

		this.stats.reset();

		this.views.get(0).preparePaint();

		for(int row = 0; row < fieldInput.getDepth(); row++) {
			for(int col = 0; col < fieldInput.getWidth(); col++) {
				Object animal = fieldInput.getObjectAt(row, col);
				if(animal != null) {
					this.stats.incrementCount(animal.getClass());
					drawMark(col, row, getColor(animal.getClass()));
				}
				else {
					drawMark(col, row, EMPTY_COLOR);
				}
			}
		}
		this.stats.countFinished();

		this.simulator.population.setText(this.simulator.POPULATION_PREFIX + this.stats.getPopulationDetails(fieldInput));

		notifyViews();
	}

	/**
	 * Determine whether the simulation should continue to run.
	 * @return true If there is more than one species alive.
	 */
	public boolean isViable(Field fieldInput)
	{
		return this.stats.isViable(fieldInput);
	}

	/**
	 * @return The color to be used for a given class of animal.
	 */
	public static Color getColor(Class<?> animalClass)
	{
		Color col = Model.colors.get(animalClass);
		if(col == null) {
			// no color defined for this class
			return UNKNOWN_COLOR;
		}
		return col;
	}

	public static void setColor(Class<?> animalClass, Color color)
	{
		colors.put(animalClass, color);
	}

	public float getCount(Class<?> actor)
	{
		return this.stats.getCount(actor);
	}

	public int getSteps(){
		return this.step;
	}
	
	public static int getWaitTime() {
		int snelheid = 1;
		if (waitTime <= 50) snelheid = 10;
		else if (waitTime <= 75) snelheid = 9;
		else if (waitTime <= 100) snelheid = 8;
		else if (waitTime <= 125) snelheid = 7;
		else if (waitTime <= 150) snelheid = 6;
		else if (waitTime <= 175) snelheid = 5;
		else if (waitTime <= 200) snelheid = 4;
		else if (waitTime <= 225) snelheid = 3;
		else if (waitTime <= 250) snelheid = 2;
		else if (waitTime <= 275) snelheid = 1;
		
		return snelheid;
	}
	
	public static void setWaitTime(int snelheid) {
		if (snelheid == 10) waitTime = 50;
		else if (snelheid == 9) waitTime = 75;
		else if (snelheid == 8) waitTime = 100;
		else if (snelheid == 7) waitTime = 125;
		else if (snelheid == 6) waitTime = 150;
		else if (snelheid == 5) waitTime = 175;
		else if (snelheid == 4) waitTime = 200;
		else if (snelheid == 3) waitTime = 225;
		else if (snelheid == 2) waitTime = 250;
		else if (snelheid == 1) waitTime = 275;
	}

	/**
	 * Methode om de applicatie te laten starten
	 */

	public void runApplication()
	{
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run()
			{
				Model.this.run = true;
				while(Model.this.run){
					simulate(1);
					pause(waitTime);
				}
			}
		});
		thread.start();
	}

	/**
	 * Stop the application
	 */
	public void stop() {
		this.run = false;
	}

	/**
	 * Pause the application
	 * @param i for how long
	 */
	public static void pause(int i) {
		try {
			Thread.sleep(i);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.run=true;
		while(this.run)
		{
			simulate(1);
			notifyViews();
			try
			{
				Thread.sleep(waitTime);
			}
			catch (Exception e)
			{
				System.out.println("Thread error (can't sleep)!");
			}
		}
	}

	@SuppressWarnings("unused")
	public static void afsluiten(){
		new SoundPlayer("src/VK/utils/sound/system-shutting-down.wav", 1);
		System.exit(0);
	}

	public void infect() {
		ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>();
		for(Actor a: this.actors){
			if (a instanceof Rabbit){
				Rabbit r = (Rabbit) a;
				rabbits.add(r);
			}
			int j = 20;
			for(int i=0; i<j; i++){
				Rabbit rabbit = rabbits.get(0);
				rabbit.makeSick();
			}
		}
	}
}