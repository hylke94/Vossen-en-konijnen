package VK.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import VK.controller.Controller;
import VK.model.Model;
import VK.view.FieldView;
import VK.view.HistogramView;
import VK.view.PieView;
import VK.view.TextView;

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
    public HistogramView hisview;
    public PieView pieview;
    public TextView txtview;
    public Model model;
    public Controller controller;
    
    public JLabel population, lblSteps, lblVersion;
    
    public JPanel eastborder;
	
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
        this.hisview = new HistogramView(this.model);
        this.pieview = new PieView(this.model);
        this.txtview = new TextView(this.model);
    	this.controller = new Controller(this.model);
    	
    	JTabbedPane jtp = new JTabbedPane();
    	jtp.addTab("Histogram", this.hisview);
    	jtp.addTab("Cirkeldiagram", this.pieview);
    	jtp.addTab("Tekstview", this.txtview);
    	
    	this.eastborder = new JPanel();
    	this.eastborder.add(jtp);
    	
    	setTitle("Vossen en Konijnen");

    	JPanel panel = new JPanel();

    	this.population = new JLabel(this.POPULATION_PREFIX, SwingConstants.CENTER);
    	this.lblSteps = new JLabel(this.STEP_PREFIX, SwingConstants.CENTER);
    	this.lblVersion = new JLabel(this.VERSION_PREFIX, SwingConstants.LEFT);

    	Container content = getContentPane();

    	content.setLayout(new BorderLayout());
    	panel.setLayout(new BorderLayout());

    	setJMenuBar(this.controller.makeMenuBar());
    	
    	panel.add(this.lblSteps, BorderLayout.NORTH);
    	panel.add(this.fieldView, BorderLayout.CENTER);
    	panel.add(this.population, BorderLayout.SOUTH);
    	
    	content.add(this.eastborder, BorderLayout.EAST);
    	content.add(this.controller.makeWestBorder(), BorderLayout.WEST);
    	content.add(panel, BorderLayout.CENTER);
    	content.add(this.lblVersion, BorderLayout.SOUTH);
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