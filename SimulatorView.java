import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author Hylke de Vries
 * @version 1.0
 */
public class SimulatorView extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	

	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private final String VERSION_PREFIX = "Version 0.0";
    private JLabel stepLabel, population, lblVersion;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    @SuppressWarnings("rawtypes")
	private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    //A JFrame for the view
    private int height, width;
    private JFrame frame;
    private JPanel panel;
    /**
     * Create a view of the given width and height.
     * @param height1 The simulation's height.
     * @param width1  The simulation's width.
     */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public SimulatorView(int height1, int width1)
    {
		this.frame = new JFrame();
		this.panel = new JPanel();
		this.height=height1;
		this.width=width1;
		
        this.stats = new FieldStats();
        this.colors = new LinkedHashMap<Class, Color>();
        
        this.frame.setTitle("Fox and Rabbit Simulation");
        this.stepLabel = new JLabel(this.STEP_PREFIX, JLabel.CENTER);
        this.population = new JLabel(this.POPULATION_PREFIX, JLabel.CENTER);
        this.lblVersion = new JLabel(this.VERSION_PREFIX, JLabel.LEFT);
        
        this.fieldView = new FieldView(this.height, this.width);
        
        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.stepLabel, BorderLayout.NORTH);
        this.panel.add(this.fieldView, BorderLayout.CENTER);
        this.panel.add(this.population, BorderLayout.SOUTH);
        this.panel.setVisible(true);
        
        this.frame.setJMenuBar(makeMenuBar());
        this.frame.add(this.panel, BorderLayout.CENTER);
        this.frame.add(this.makeWestBorder(), BorderLayout.WEST);
        this.frame.add(this.lblVersion, BorderLayout.SOUTH);
        this.frame.pack();
        this.frame.setVisible(true);
        
        int[] location = centerFrame();
        this.frame.setLocation(location[0], location[1]);
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public int[] centerFrame(){
		int[] place = new int[] {1,1};
        int frameHeight = this.frame.getHeight();
        int frameWidth = this.frame.getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;

        place[0] = ((screenWidth-frameWidth)/2);
        place[1] = ((screenHeight-frameHeight)/2);
        
        return place;
    }
	
	/**
	 * Makes a JPanel with a BoxLayout.
	 * Contains buttons
	 * 
	 * @return JPanel
	 */
	private static JPanel makeWestBorder() {
		JPanel panel = new JPanel();
		JPanel westborder = new JPanel();
		
		//Make buttons
		JButton btnStart1 = new JButton("Step 1");
		btnStart1.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						Simulator.simulateOneStep();
					}	
				}
		);
		JButton btnStart100 = new JButton("Step 100");
		btnStart100.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						Simulator.simulate(100);
					}	
				}
		);
		final JTextField aantalStappen = new JTextField();
		JButton btnSimuleer = new JButton("Simuleer");
		btnSimuleer.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						try{
							String aantalstappen = aantalStappen.getText();
							int aantal = Integer.parseInt(aantalstappen);
							
							if (aantal<=0)
								System.out.println("Aantal dagen mag geen 0 zijn!");
							else{
								Simulator.simulate(aantal);
							}
						}
						catch (Exception exc){
							System.out.println("Voer een positief getal in!");
						}
						
					}	
				}
		);
		
		//Make frames
		panel.add(btnStart1);
		panel.add(btnStart100);
		panel.add(aantalStappen);
		panel.add(btnSimuleer);
		panel.setLayout(new GridLayout(0,1));
		
		westborder.add(panel);
		westborder.setVisible(true);
		
		return westborder;
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

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        this.stepLabel.setText(this.STEP_PREFIX + step);
        this.stats.reset();
        
        this.fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    this.stats.incrementCount(animal.getClass());
                    this.fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    this.fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        this.stats.countFinished();

        this.population.setText(this.POPULATION_PREFIX + this.stats.getPopulationDetails(field));
        this.fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return this.stats.isViable(field);
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
		private static final long serialVersionUID = 1L;

		private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height1, int width1)
        {
            this.gridHeight = height1;
            this.gridWidth = width1;
            this.size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        @Override
		public Dimension getPreferredSize()
        {
            return new Dimension(this.gridWidth * this.GRID_VIEW_SCALING_FACTOR,
                                 this.gridHeight * this.GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        @SuppressWarnings("synthetic-access")
		public void preparePaint()
        {
            if(! this.size.equals(getSize())) {  // if the size has changed...
                this.size = getSize();
                this.fieldImage = SimulatorView.this.fieldView.createImage(this.size.width, this.size.height);
                this.g = this.fieldImage.getGraphics();

                this.xScale = this.size.width / this.gridWidth;
                if(this.xScale < 1) {
                    this.xScale = this.GRID_VIEW_SCALING_FACTOR;
                }
                this.yScale = this.size.height / this.gridHeight;
                if(this.yScale < 1) {
                    this.yScale = this.GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            this.g.setColor(color);
            this.g.fillRect(x * this.xScale, y * this.yScale, this.xScale-1, this.yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        @Override
		public void paintComponent(Graphics g1)
        {
            if(this.fieldImage != null) {
                Dimension currentSize = getSize();
                if(this.size.equals(currentSize)) {
                    g1.drawImage(this.fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g1.drawImage(this.fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getActionCommand();
		
		if (command!=null){
			System.out.println("Geen actie voor command bekend!\n	Command = \""+command+"\";\n	"+e+"\n");
		}
		else{
			System.out.println("Command is 'null'!\n	"+e);
		}
	}
}

