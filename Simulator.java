import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Hylke de Vries
 * @version 1.0
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;
    // The depth and with for the grid.
    private int width = 0;
    private int depth = 0;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.05;    
    // The probability that a bear will be created in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.01;    

    // List of animals in the field.
    private static List<Animal> animals;
    // List of animals in the field.
    private static List<Hunter> hunters;
    // The current state of the field.
    private static Field field;
    // The current step of the simulation.
    private static int step;
    // A graphical view of the simulation.
    private static SimulatorView simview;
    
    // Variabels for the animated simulation
    private static boolean run;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth1 Depth of the field. Must be greater than zero.
     * @param width1 Width of the field. Must be greater than zero.
     */
    public Simulator(int depth1, int width1)
    {
        if(width1 <= 0 || depth1 <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            this.depth = DEFAULT_DEPTH;
            this.width = DEFAULT_WIDTH;
        }
        else {
        	this.depth=depth1;
        	this.width=width1;
        }
        
        Simulator.animals = new ArrayList<Animal>();
        Simulator.hunters = new ArrayList<Hunter>();
        Simulator.field = new Field(this.depth, this.width);

        // Create a view of the state of each location in the field.
        Simulator.simview = new SimulatorView(this.depth, this.width);
        Simulator.simview.setColor(Rabbit.class, Color.orange);
        Simulator.simview.setColor(Fox.class, Color.blue);
        Simulator.simview.setColor(Bear.class, Color.red);
        Simulator.simview.setColor(Hunter.class, Color.black);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public static void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public static void simulate(int numSteps)
    {
        for(int step1 = 1; step1 <= numSteps && Simulator.simview.isViable(Simulator.field); step1++)
        simulateOneStep();
    }

	/**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public static void simulateOneStep()
    {
        Simulator.step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all rabbits act.
        try{
        	for(Iterator<Animal> it = Simulator.animals.iterator(); it.hasNext(); ) {
        		Animal animal = it.next();
        		animal.act(newAnimals);
        		if(! animal.isAlive()) {
        			it.remove();
        		}
        	}
        }
        catch (Exception e){
        	System.out.println(e);
        }
               
        // Let all rabbits act.
        for(Iterator<Hunter> it = Simulator.hunters.iterator(); it.hasNext(); ) {
            Hunter hunter = it.next();
            hunter.act();
            if(! hunter.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        Simulator.animals.addAll(newAnimals);

        Simulator.simview.showStatus(Simulator.step, Simulator.field);
        
        //pause(100);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public static void reset()
    {
    	if (run==false){
    		Simulator.step = 0;
    		Simulator.animals.clear();
    		populate();
    		
    		// Show the starting state in the view.
    		Simulator.simview.showStatus(Simulator.step, Simulator.field);
    	}
    	else{
    		System.out.println("U kunt nu niet resetten, want de simulatie drait nog!");
    	}
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private static void populate()
    {
        Random rand = Randomizer.getRandom();
        Simulator.field.clear();
        for(int row = 0; row < Simulator.field.getDepth(); row++) {
            for(int col = 0; col < Simulator.field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, Simulator.field, location);
                    Simulator.animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, Simulator.field, location);
                    Simulator.animals.add(rabbit);
                }
                else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bear bear = new Bear(true, Simulator.field, location);
                    Simulator.animals.add(bear);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hunter hunter = new Hunter(true, Simulator.field, location);
                    Simulator.hunters.add(hunter);
                }
                // else leave the location empty.
            }
        }
    }
    
    public static void setRun(boolean b){
    	run = b;
    }
    
    public static boolean getRun(){
    	return run;
    }
    
    /**
     * Methode om de applicatie te laten runnen
     */
    public static void start(){
    	Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
    			setRun(true);
    			while (getRun()==true){
    				simulate(1);
    				//simulateOneStep();
    				pause(100);
    			}
    		}
    	});
    	thread.start();
    }
    public static void stop(){
    	run = false;
    }
    
	public static void pause(int i) {
    	try {
    		Thread.sleep(i);}
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	}
}

