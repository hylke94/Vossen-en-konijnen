package VK.model;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

import VK.actors.Actor;
import VK.actors.Bear;
import VK.actors.Fox;
import VK.actors.Hunter;
import VK.actors.Rabbit;
import VK.actors.Randomizer;
import VK.main.Simulator;
import VK.view.Field;
import VK.view.FieldStats;
import VK.view.Location;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

public class Model extends AbstractModel implements Runnable {
	
	
	
	// Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 150;
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
    
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;
    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;
    
    // List of animals in the field.
    private ArrayList<Actor> actors;
    
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    public int step;
    // A graphical view of the simulation.
    
    public boolean run = false;
    
    @SuppressWarnings("rawtypes")
	private LinkedHashMap<Class, Color> colors;

    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    private Simulator simulator;
    
	public Model(Simulator newSimulator){
		this(DEFAULT_DEPTH, DEFAULT_WIDTH);
		
		this.simulator = newSimulator;
	}
	
	@SuppressWarnings("rawtypes")
	public Model(int depth, int width){
		super();
		
		if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        this.actors = new ArrayList<Actor>();
        this.field = new Field(depth, width);
       
        this.stats = new FieldStats();
        
        this.colors = new LinkedHashMap<Class, Color>();
       
        // Create a view of the state of each location in the field
        setColor(Rabbit.class, Color.orange);
        setColor(Fox.class, Color.blue);
        setColor(Bear.class, Color.red);
        setColor(Hunter.class, Color.black);
	}
	
	/**
	 * Starts a thread for the simulation
	 */
	public void start() {
		new Thread(this).start();
	}
	
	/**
	 * Let the simulation stop
	 */
	public void stop() {
		this.run=false;
	}
	 
	/**
	 * Start the simulation
	 */
	@Override
	public void run() {
		this.run=true;
		while(this.run) {
			simulate(1);
			notifyViews();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("Thread error (can't sleap)!");
			} 
		}
	}
	
	/**
	 * Run the simulation for "i" steps
	 * @param i
	 */
	public void simulate(int i) {
		for (@SuppressWarnings("unused")
		int steps = 1; this.step <= i && isViable(this.field); steps++) simulateOneStep();
	}
	
	/**
	 * Run the simulation for 1 step
	 */
	public void simulateOneStep() {
		this.step++;

        // Provide space for newborn animals.
        ArrayList<Actor> newActors = new ArrayList<Actor>();
        // Let all rabbits act.
        for(Iterator<Actor> it = this.actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newActors);
            if(! actor.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        this.actors.addAll(newActors);

        showStatus(this.step, this.field);
	}
	
	/**
	 * Reset the simulation
	 */
	@Override
	public void reset() {
		this.step = 0;
	     
        this.actors.clear();
        populate();
        
        // Show the starting state in the view.
        showStatus(this.step, this.field);
	}
	
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
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
                    Bear bear = new Bear(true, this.field, location);
                    this.actors.add(bear);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hunter hunter = new Hunter(true, this.field, location);
                    this.actors.add(hunter);
                // else leave the location empty.
                }
            }
        }
    }
    
    /**
    * Paint on grid location on this field in a given color.
    */
 	public void drawMark(int x, int y, Color color) {
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
 	        
 	        Simulator.lblSteps.setText(this.simulator.STEP_PREFIX + stepInput);
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
 	        Simulator.population.setText(this.simulator.POPULATION_PREFIX + this.stats.getPopulationDetails(fieldInput));
 	        
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
 	   * Define a color to be used for a given class of animal.
 	   * @param animalClass The animal's Class object.
 	   * @param color The color to be used for the given class.
 	   */
       @SuppressWarnings("rawtypes")
       public void setColor(Class animalClass, Color color)
 	       {
 	           this.colors.put(animalClass, color);
 	       }

       /**
 	   * @return The color to be used for a given class of animal.
 	   */
       @SuppressWarnings("rawtypes")
       private Color getColor(Class animalClass)
 	       {
 	           Color col = this.colors.get(animalClass);
 	           if(col == null) {
 	               // no color defined for this class
 	               return UNKNOWN_COLOR;
 	           }
			return col;
 	       }
}