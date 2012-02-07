package VK.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import VK.controller.Controller;
import VK.model.Model;
import VK.view.FieldView;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Hylke de Vries
 * @version 0.0
 */
@SuppressWarnings("serial")
public class Simulator extends JFrame
{
    public FieldView fieldView;
    public Model model;
    public Controller controller;
    
    public JLabel population, lblSteps, lblVersion;
	
    public final String STEP_PREFIX = "Step: ";
    public final String POPULATION_PREFIX = "Population: ";
    public final String VERSION_PREFIX = "Version 0.0";
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this.model = new Model(this);
    	this.fieldView = new FieldView(this.model);
    	this.controller = new Controller(this.model);
    	
    	setTitle("Vossen en Konijnen");

    	JPanel panel = new JPanel();

    	this.population = new JLabel(this.POPULATION_PREFIX, SwingConstants.CENTER);
    	this.lblSteps = new JLabel(this.STEP_PREFIX, SwingConstants.CENTER);

    	Container content = getContentPane();

    	content.setLayout(new BorderLayout());
    	panel.setLayout(new BorderLayout());

    	setJMenuBar(this.controller.makeMenuBar());

    	panel.add(this.controller.makeEastBorder(), BorderLayout.EAST);
    	panel.add(this.controller.makeWestBorder(), BorderLayout.WEST);
    	
    	panel.add(this.lblSteps, BorderLayout.NORTH);
    	panel.add(this.fieldView, BorderLayout.CENTER);
    	panel.add(this.population, BorderLayout.SOUTH);
    	content.add(panel, BorderLayout.SOUTH);
    	setResizable(false);
    	pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    	this.model.reset();
        
        int[] location = centerFrame(this);
        this.setLocation(location[0], location[1]);
    }
	
	public static int[] centerFrame(JFrame frame){
		int[] place = new int[2];
        int frameHeight = frame.getHeight();
        int frameWidth = frame.getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;

        place[0] = ((screenWidth-frameWidth)/2);
        place[1] = ((screenHeight-frameHeight)/2);
        
        return place;
    }
}