package VK.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import VK.actors.Bear;
import VK.actors.Fox;
import VK.actors.Grass;
import VK.actors.Hunter;
import VK.actors.Rabbit;
import VK.model.Model;
import VK.view.Legenda;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {
	
	private JButton btnStart1, btnStart100, btnSimuleer, btnStart, btnStop, btnReset;
	private JTextField aantalStappen;

	public Controller(Model newModel) {
		super(newModel);

		this.add(makeMenuBar());
		this.add(makeWestBorder());
		this.add(makeEastBorder());
	}
	
	/**
	 * Makes a JPanel with a BoxLayout.
	 * Contains buttons
	 * 
	 * @return JPanel
	 */
	public JPanel makeWestBorder() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel westborder = new JPanel();

		panel1.setLayout(new GridLayout(0,1));
		panel2.setLayout(new GridLayout(0,1));
		panel3.setLayout(new GridLayout(0,1));
		
		//Make buttons
		this.btnStart1 = new JButton("Step 1");
		this.btnStart1.addActionListener(this);
		
		this.btnStart100 = new JButton("Step 100");
		this.btnStart100.addActionListener(this);
		
		this.aantalStappen = new JTextField();
		
		this.btnSimuleer = new JButton("Simuleer");
		this.btnSimuleer.addActionListener(this);
		
		this.btnStart = new JButton("Start");
		this.btnStart.addActionListener(this);
		
		this.btnStop = new JButton("Stop");
		this.btnStop.addActionListener(this);
		
		this.btnReset = new JButton("Reset");
		this.btnReset.addActionListener(this);
		
		// An empty label, to create an empty lines
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		JLabel emptyLabel3 = new JLabel();
		JLabel emptyLabel4 = new JLabel();
		
		//Make frames
		panel2.add(this.btnStart1);
		panel2.add(this.btnStart100);
		
		panel2.add(emptyLabel1);
		panel2.add(this.aantalStappen);
		panel2.add(this.btnSimuleer);
		
		panel2.add(emptyLabel2);
		panel2.add(this.btnStart);
		panel2.add(this.btnStop);
		
		panel2.add(emptyLabel3);
		panel2.add(this.btnReset);
		
		// Labels for every actor
		JLabel lblLegenda = new JLabel("Legenda", SwingConstants.CENTER);
		JLabel lblRabbits = new JLabel("Rabbits", SwingConstants.CENTER);
			lblRabbits.setForeground(Model.getColor(Rabbit.class));
			lblRabbits.setOpaque(true);
			lblRabbits.setBackground(Color.white);
		JLabel lblFoxes = new JLabel("Foxes", SwingConstants.CENTER);
			lblFoxes.setForeground(Model.getColor(Fox.class));
			lblFoxes.setOpaque(true);
			lblFoxes.setBackground(Color.white);
		JLabel lblBears = new JLabel("Bears", SwingConstants.CENTER);
			lblBears.setForeground(Model.getColor(Bear.class));
			lblBears.setOpaque(true);
			lblBears.setBackground(Color.white);
		JLabel lblHunters = new JLabel("Hunters", SwingConstants.CENTER);
			lblHunters.setForeground(Model.getColor(Hunter.class));
			lblHunters.setOpaque(true);
			lblHunters.setBackground(Color.white);
		JLabel lblGrass = new JLabel("Grass", SwingConstants.CENTER);
			lblGrass.setForeground(Model.getColor(Grass.class));
			lblGrass.setOpaque(true);
			lblGrass.setBackground(Color.white);
		
		panel3.add(emptyLabel4);
		panel3.add(lblLegenda);
		panel3.add(lblRabbits);
		panel3.add(lblFoxes);
		panel3.add(lblBears);
		panel3.add(lblHunters);
		panel3.add(lblGrass);
		
		panel1.add(panel2);
		panel1.add(panel3);
		
		westborder.add(panel1);
		westborder.setVisible(true);
		
		return westborder;
	}
    
	/**
	* Makes the menubar
	*
	* @return JMenuBar
	*/
	public JMenuBar makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuMenu1 = new JMenu("Bestand");
		final JMenuItem quitItem = new JMenuItem("Afsluiten");
		quitItem.addActionListener(this);
		menuMenu1.add(quitItem);
		menuBar.add(menuMenu1);
	
		JMenu menuHelp = new JMenu("Help");
		final JMenuItem legendaItem = new JMenuItem("Legenda");
		menuHelp.add(legendaItem);
		legendaItem.addActionListener(this);
		menuBar.add(menuHelp);
	
		return menuBar;
	}
    
    /**
    * Creates a JPanel with a BoxLayout to facilitate the Graphics
    * @return
    */

    public JPanel makeEastBorder(){
	    JPanel panel = new JPanel();
	    JPanel eastborder = new JPanel();
	
	    final JButton btnTest = new JButton("Test");
	    btnTest.addActionListener(this);
	
	    panel.add(btnTest);
	    panel.setLayout(new GridLayout(0,3));
	
	    eastborder.add(panel);
	    eastborder.setVisible(true);
	
	    return eastborder;
    }

    /**
    * Handles the events for every action performed
    */

    @Override
    public void actionPerformed(ActionEvent e) {
	    //--------- Knoppen
	
	    if(e.getActionCommand() == "Step 1"){
	    	this.model.simulateOneStep();
	    }
	    if(e.getActionCommand() == "Step 100"){
	    	this.model.simulate(100);
	    }
	    if(e.getActionCommand() == "Simuleer"){
	    	try{
	    		String steps = this.aantalStappen.getText();
	    		int aantal = Integer.parseInt(steps);

	    		if (! this.model.run) this.model.simulate(aantal);
	    		else System.out.println("U moet eerst de simulatie stoppen!");
	    	}
	    	catch (Exception exc){
	    		System.out.println("Voer een positief getal in!");
	    	}
    	}
    	if (e.getActionCommand() == "Start"){
    		if (! this.model.run) this.model.runApplication();
	    }
	    if (e.getActionCommand() == "Stop"){
	    	if (this.model.run) this.model.stop();
	    }
	    if (e.getActionCommand() == "Reset"){
	    	if (this.model.run) this.model.stop();
	    	this.model.reset();
	    }
	
	    if (e.getActionCommand() == "Test"){
	    	System.out.println("Test gelukt");
	    }
	
	    //--- Menu items
	
	    if (e.getActionCommand() == "Legenda"){
	    	@SuppressWarnings("unused")
			Legenda lgd = new Legenda(new JFrame(), "Legenda");
	    }
	    if(e.getActionCommand() == "Afsluiten"){
	    	Model.afsluiten();
	    }
    }
}
