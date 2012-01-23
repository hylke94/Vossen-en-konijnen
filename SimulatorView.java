import java.awt.*;

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
public class SimulatorView extends JPanel
{
	private static final long serialVersionUID = 1L;
	

	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    @SuppressWarnings("rawtypes")
	private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    //A JFrame for the view
    private JFrame frame;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public SimulatorView(int height, int width)
    {
		this.frame = new JFrame();
		
        this.stats = new FieldStats();
        this.colors = new LinkedHashMap<Class, Color>();
        
        //setTitle("Fox and Rabbit Simulation");
        this.stepLabel = new JLabel(this.STEP_PREFIX, JLabel.CENTER);
        this.population = new JLabel(this.POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(500, 25);
        
        this.fieldView = new FieldView(height, width);

        this.frame.add(this.stepLabel, BorderLayout.NORTH);
        this.frame.add(this.fieldView, BorderLayout.CENTER);
        this.frame.add(this.population, BorderLayout.SOUTH);
        this.frame.pack();
        this.frame.setVisible(true);
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
        public FieldView(int height, int width)
        {
            this.gridHeight = height;
            this.gridWidth = width;
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
}

