package VK.simulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import VK.actors.Hunter;
import VK.animals.Animal;
import VK.animals.Bear;
import VK.animals.Fox;
import VK.animals.Rabbit;
import VK.controller.AbstractController;
import VK.controller.Controller;
import VK.main.RunException;
import VK.model.Model;
import VK.view.AbstractView;
import VK.view.AnimatedView;
import VK.view.TextView;
import VK.view.View;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Hylke de Vries
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Simulator implements ActionListener
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;
    // The depth and with for the grid.
    public static int width = 0;
    public static int heigth = 0;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.05;    
    // The probability that a bear will be created in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.02;    

    // List of animals in the field.
    private static List<Animal> animals;
    // List of animals in the field.
    private static List<Hunter> hunters;
    // The current state of the field.
    private static Field field;
    // The current step of the simulation.
    private static int step;
    // A graphical view of the simulation.
    private static AnimatedView anview;
    
    // Variabels for the animated simulation
    private static boolean run;
    
    //A frame for the simulation
    private final String VERSION_PREFIX = "Version 0.0";
    private JLabel lblVersion;
    private AnimatedView animatedView;
    
    /**
     * Construct a simulation field with default size.
     * @throws RunException 
     */
    public Simulator() throws RunException
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth1 Depth of the field. Must be greater than zero.
     * @param width1 Width of the field. Must be greater than zero.
     * @throws RunException 
     */
	public Simulator(int newHeight, int newWidth) throws RunException
    {
        Model model = new Model();
    	AbstractView view = new View(model);
    	AbstractController controller = new Controller(model);
    	
    	if(newHeight <= 0 || newWidth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            Simulator.heigth = DEFAULT_DEPTH;
            Simulator.width = DEFAULT_WIDTH;
        }
        else {
        	Simulator.heigth=newHeight;
        	Simulator.width=newWidth;
        }
        
        Simulator.animals = new ArrayList<Animal>();
        Simulator.hunters = new ArrayList<Hunter>();
        Simulator.field = new Field(Simulator.heigth, Simulator.width);
        
        // Create a view of the state of each location in the field.
        Simulator.anview = new AnimatedView(model);
        Simulator.anview.setColor(Rabbit.class, Color.orange);
        Simulator.anview.setColor(Fox.class, Color.blue);
        Simulator.anview.setColor(Bear.class, Color.red);
        Simulator.anview.setColor(Hunter.class, Color.black);
        
        //Simulator.anview = new TextView();
        
        
        // Create the window with th content
		JFrame screen = new JFrame();
		
		screen.setTitle("Fox and Rabbit Simulation");
        this.lblVersion = new JLabel(this.VERSION_PREFIX, SwingConstants.LEFT);
        
        this.animatedView = new AnimatedView(model);
        
        screen.setJMenuBar(makeMenuBar());
        screen.add(view, BorderLayout.CENTER);
        screen.add(controller, BorderLayout.WEST);
        screen.add(this.lblVersion, BorderLayout.SOUTH);
        screen.pack();
        screen.setVisible(true);
        
        int[] location = centerFrame(screen);
        screen.setLocation(location[0], location[1]);
        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setup a valid starting point.
        reset();
    }

	/**
	 * Makes the menubar
	 * 
	 * @return JMenuBar
	 */
	private JMenuBar makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuMenu1 = new JMenu("Menu1");
			JMenuItem openItem = new JMenuItem("Openen");
			openItem.addActionListener(this);
			menuMenu1.add(openItem);
			JMenuItem quitItem = new JMenuItem("Afsluiten");
			quitItem.addActionListener(this);
			menuMenu1.add(quitItem);
				menuBar.add(menuMenu1);
		JMenu menuMenu2 = new JMenu("Menu2");
			JMenuItem menu2Item = new JMenuItem("Menu2Item");
			menuMenu2.add(menu2Item);
				menuBar.add(menuMenu2);
		JMenu menuHelp = new JMenu("Help");
			JMenuItem helpItem = new JMenuItem("Help");
			menuHelp.add(helpItem);
				menuBar.add(menuHelp);
		
		return menuBar;
	}
	
	public static int[] centerFrame(JFrame frame){
		int[] place = new int[] {1,1};
        int frameHeight = frame.getHeight();
        int frameWidth = frame.getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;

        place[0] = ((screenWidth-frameWidth)/2);
        place[1] = ((screenHeight-frameHeight)/2);
        
        return place;
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     * @throws RunException 
     */
    public static void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     * @throws RunException 
     */
    public static void simulate(int numSteps)
    {
        for(int step1 = 1; step1 <= numSteps && Simulator.anview.isViable(Simulator.field); step1++)
        simulateOneStep();
    }

	/**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
	 * @throws RunException 
     */
    public static void simulateOneStep()
    {
        Simulator.step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all animals act.
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
               
        // Let all hunters act.
        for(Iterator<Hunter> it = Simulator.hunters.iterator(); it.hasNext(); ) {
            Hunter hunter = it.next();
            hunter.act();
            if(! hunter.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born animals to the main lists.
        Simulator.animals.addAll(newAnimals);

        Simulator.anview.showStatus(Simulator.step, Simulator.field);
    }
        
    /**
     * Reset the simulation to a starting position.
     * @throws RunException 
     */
    public static void reset()
    {
    	if (run==false){
    		Simulator.step = 0;
    		Simulator.animals.clear();
    		populate();
    		
    		// Show the starting state in the view.
    		Simulator.anview.showStatus(Simulator.step, Simulator.field);
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
    				pause(50);
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

	@Override
	public void actionPerformed(ActionEvent e){
		Object command = e.getActionCommand();
		
		if (command=="Afsluiten"){
			System.exit(0);
		}
		else{
			System.out.println("Geen actie voor command bekend!\n	Command = \""+command+"\";\n	"+e+"\n");
		}
	}
}

