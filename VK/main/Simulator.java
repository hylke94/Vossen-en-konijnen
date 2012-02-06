package VK.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import VK.controller.AbstractController;
import VK.controller.Controller;
import VK.model.AbstractModel;
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
public class Simulator extends JFrame implements ActionListener
{
    private FieldView fieldView;
    private AbstractModel model;
    private AbstractController controller;
    
    public static JLabel population, lblSteps;
	public JLabel lblVersion;
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

    	population = new JLabel(this.POPULATION_PREFIX, SwingConstants.CENTER);
    	lblSteps = new JLabel(this.STEP_PREFIX, SwingConstants.CENTER);

    	Container content = getContentPane();

    	content.setLayout(new BorderLayout());
    	panel.setLayout(new BorderLayout());

    	setJMenuBar(this.controller.makeMenuBar());

    	panel.add(this.controller.makeEastBorder(), BorderLayout.EAST);
    	panel.add(this.controller.makeWestBorder(), BorderLayout.WEST);
    	
    	panel.add(lblSteps, BorderLayout.NORTH);
    	panel.add(this.fieldView, BorderLayout.CENTER);
    	panel.add(population, BorderLayout.SOUTH);
    	content.add(panel,BorderLayout.SOUTH);
    	setResizable(true);
    	pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    	this.model.reset();
        
        int[] location = centerFrame(this);
        this.setLocation(location[0], location[1]);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public JMenuBar makeMenuBar(){
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
	
	@Override
	public void repaint() {
		super.setTitle("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

