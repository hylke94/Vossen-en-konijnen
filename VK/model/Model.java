package VK.model;
import VK.main.RunException;
import VK.simulator.Simulator;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

public class Model extends AbstractModel implements Runnable {
	
	private int steps;
	private boolean run;
	
	/**
	 * Constructor
	 * Set everything on zero/false
	 */
	public Model() {
		this.steps = 0;
		this.run = false;
	}
	
	//----------Getters
	
	/**
	 * Getter for the steps
	 * @return steps
	 */
	public int getSteps() {
		return this.steps;
	}
	
	/**
	 * Getter for the run-boolean
	 * @return run
	 */
	public boolean getRun() {
		return this.run;
	}
	
	//----------Setters
	
	/**
	 * Sets the variable step
	 * @param newSteps
	 */
	public void setSteps(int newSteps){
		this.steps=newSteps;
	}
	
	//----------Voids
	
	/**
	 * Increases the int steps with 1
	 * @throws RunException
	 */
	public void incrementSteps() throws RunException {
		setSteps(getSteps()+1);
		notifyViews();
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
	 * Run the simulation for 1 step
	 * @throws RunException
	 */
	public void simulateOneStep() {
		Simulator.simulateOneStep();
		notifyViews();
	}
	
	/**
	 * Run the simulation for "i" steps
	 * @param i
	 * @throws RunException
	 */
	public void simulate(int i) {
		Simulator.simulate(i);
		notifyViews();
	}
	
	/**
	 * Reset the simulation
	 * @throws RunException
	 */
	public void reset() {
		Simulator.reset();
		notifyViews();
	}
}